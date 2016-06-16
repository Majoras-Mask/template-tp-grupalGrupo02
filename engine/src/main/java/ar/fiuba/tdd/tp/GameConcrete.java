package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.timer.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameConcrete implements Game, Context{

    private static final long TIME_TICK_MS = 1000; // 1 segundo.
    private List<ObjectInterface> objects = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<String,String>();
    private GameState gameState = GameState.Running;
    private List<Timer> timers = new ArrayList<>();
    private HashMap<String, Condition> winConditions = new HashMap<>();
    private HashMap<String, Condition> lostConditions = new HashMap<>();
    private List<String> playerIDSHaveLost = new ArrayList<>();
    private List<String> playerIDS = new ArrayList<>();
    private List<String> playerIDSAvailable = new ArrayList<>();
    private Sender sender = new SenderNull();
    private RandomGenerator randomGenerator = new RandomGeneratorDefault();

    @Override
    public synchronized void addObject(ObjectInterface object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public synchronized void addCommand(Command command) {
        if (!commands.contains(command)) {
            commands.add(command);
        }
    }

    private synchronized void setUpHashMap(String playerName) {
        map.put("(player)", playerName);
    }

    private synchronized void sendAllExcept(String message, String playerException) {
        for (String playerID: playerIDS) {
            if (!playerID.equals(playerException) && !playerIDSAvailable.contains(playerID)) {
                sender.send(playerID, message);
            }
        }
    }

    private synchronized void logExecuteCommand(String playerName, String commandString) {
        String message = playerName.concat(" execute: ");
        message = message.concat(commandString);
        sendAllExcept(message, playerName);
    }

    private synchronized boolean checkIfGameIsFinished() {
        return getGameState() == GameState.Win  || getGameState() == GameState.Lost;
    }


    private synchronized String processCommand(String playerName, String commandString) {
        String response = "No command found.";

        for (Command command: commands) {
            if (command.matches(commandString)) {
                response = command.execute(commandString, this);
                break;
            }
        }

        checkGameConditions();

        if (checkIfGameIsFinished() || playerIDSHaveLost.contains(playerName)) {
            return "Game finished";
        }

        return response;
    }

    @Override
    public synchronized String executeCommand(String playerName, String commandString) {
        if (!playerIDS.contains(playerName)) {
            return "You aren't a valid player.";
        }

        if (checkIfGameIsFinished() || playerIDSHaveLost.contains(playerName)) {
            return "Game finished";
        }

        logExecuteCommand(playerName, commandString);
        setUpHashMap(playerName);

        return processCommand(playerName, commandString);
    }

    private synchronized void clearFinishedTimers() {
        List<Timer> timersToDelete = new ArrayList<>();
        for (Timer timer : timers) {
            if (timer.isFinished()) {
                timersToDelete.add(timer);
            }
        }

        for (Timer timer : timersToDelete) {
            timers.remove(timer);
        }
    }


    private synchronized String checkConditions(HashMap<String, Condition> conditions) {
        for (Map.Entry<String, Condition> entry : conditions.entrySet()) {
            String playerId = entry.getKey();
            Condition condition = entry.getValue();
            if (condition.check(this)) {
                return playerId;
            }
        }
        return null;
    }

    private synchronized void checkWinConditions() {
        String playerID = checkConditions(winConditions);
        if (playerID != null) {
            setGameState(GameState.Win);
            sender.sendAll(playerID.concat(" has Won. The game is over."));
        }
    }

    private synchronized boolean checkIfAllPlayersHaveLost() {
        return playerIDSHaveLost.size() == playerIDS.size();
    }

    private synchronized void checkLostConditions() {
        String playerID = checkConditions(lostConditions);
        if (playerID != null) {
            lostConditions.remove(playerID);
            playerIDSHaveLost.add(playerID);
            if (checkIfAllPlayersHaveLost()) {
                setGameState(GameState.Lost);
                sender.sendAll(playerID.concat(" has Lost. All player have lost. The game is over."));
            } else {
                sender.sendAll(playerID.concat(" has Lost."));
            }
        }
    }


    private synchronized void checkGameConditions() {
        checkWinConditions();
        checkLostConditions();
    }

    public synchronized void update(long milliseconds) {
        if (checkIfGameIsFinished()) {
            return;
        }

        List<Timer> timersCopy = new ArrayList<>(timers);

        for (Timer timer:timersCopy) {
            timer.update(this, sender, milliseconds);
        }

        clearFinishedTimers();

        checkGameConditions();
    }

    @Override
    public synchronized void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public synchronized GameState getGameState() {
        return gameState;
    }

    @Override
    public synchronized void addTimer(Timer timer) {
        this.timers.add(timer);
    }

    @Override
    public synchronized void setWinCondition(String playerID, Condition winCondition) {
        this.winConditions.put(playerID, winCondition);
    }

    @Override
    public synchronized void setLostCondition(String playerID, Condition lostCondition) {
        this.lostConditions.put(playerID, lostCondition);
    }

    @Override
    public synchronized void addPlayer(ObjectInterface player) {
        addObject(player);
        if (!playerIDS.contains(player.getDescription())) {
            playerIDS.add(player.getDescription());
            playerIDSAvailable.add(player.getDescription());
        }
    }

    @Override
    public synchronized String getPlayerIDAvailable() {
        if (playerIDSAvailable.size() > 0) {
            String playerID = playerIDSAvailable.get(0);
            playerIDSAvailable.remove(0);
            return playerID;
        }
        return "";
    }

    @Override
    public synchronized void leavePlayer(String playerID) {
        if (!playerIDSAvailable.contains(playerID)) {
            playerIDSAvailable.add(playerID);
        }
    }

    @Override
    public synchronized void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public synchronized void startLoop() {
        ThreadUpdater threadUpdater = new ThreadUpdater(this,TIME_TICK_MS);
        threadUpdater.start();
    }

    @Override
    public Boolean playerLose(String playerId) {
        return this.playerIDSHaveLost.contains(playerId);
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    @Override
    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public synchronized ObjectInterface getObject(String name) {
        if (map.containsKey(name)) {
            name = map.get(name);
        }

        for (ObjectInterface object : objects) {
            if (object.getDescription().equals(name)) {
                return object;
            }
        }
        return ObjectNull.getInstance();
    }

}
