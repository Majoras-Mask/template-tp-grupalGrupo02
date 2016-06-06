package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.timer.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameConcrete implements Game, Context {

    private List<ObjectInterface> objects = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<String,String>();
    private GameState gameState = GameState.Running;
    private List<Timer> timers = new ArrayList<>();
    private HashMap<String, Condition> winConditions = new HashMap<>();
    private HashMap<String, Condition> lostConditions = new HashMap<>();
    private List<String> playerIDS = new ArrayList<>();
    private Sender sender;
    private int indexPlayerIDAvailable = 0;
    private String playerWhoFinishedTheGame;

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

    private void setUpHashMap(String playerName) {
        map.put("(player)", playerName);
    }

    private void sendAllExcept(String message, String playerException) {
        for (String playerID: playerIDS) {
            if (!playerID.equals(playerException)) {
                sender.send(playerID, message);
            }
        }
    }

    private void logExecuteCommand(String playerName, String commandString) {
        String message = playerName.concat(" execute: ");
        message = message.concat(commandString);
        sendAllExcept(message, playerName);
    }

    private boolean checkIfGameIsFinished() {
        return getGameState() == GameState.Win  || getGameState() == GameState.Lost;
    }

    private String checkPostExecuteCommand(String playerName, String response) {
        checkConditions(winConditions, GameState.Win);
        checkConditions(lostConditions, GameState.Lost);

        if (getGameState() == GameState.Running) {
            return response;
        } else if (getGameState() == GameState.Win) {
            String message = playerWhoFinishedTheGame.concat(" won the game.");
            sendAllExcept(message, playerName);
            return message;
        } else {
            String message = playerWhoFinishedTheGame.concat(" has Lost. The game is over.");
            sendAllExcept(message, playerName);
            return message;
        }

    }

    @Override
    public synchronized String executeCommand(String playerName, String commandString) {
        if (checkIfGameIsFinished()) {
            return "Game finished";
        }

        logExecuteCommand(playerName, commandString);
        setUpHashMap(playerName);

        String response = "No command found.";

        for (Command command: commands) {
            if (command.matches(commandString)) {
                response = command.execute(commandString, this);
                break;
            }
        }

        return checkPostExecuteCommand(playerName, response);
    }

    private void clearFinishedTimers() {
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

    private void logGameFinishedBy(String playerId, GameState gameState) {
        if (gameState == GameState.Win) {
            sender.sendAll(playerId.concat(" has Won."));
        } else if (gameState == GameState.Lost) {
            sender.sendAll(playerId.concat(" has Lost."));
        }
    }

    private void checkConditions(HashMap<String, Condition> conditions, GameState gameStateToSet) {
        for (Map.Entry<String, Condition> entry : conditions.entrySet()) {
            String playerId = entry.getKey();
            Condition condition = entry.getValue();
            if (condition.check(this)) {
                setGameState(gameStateToSet);
                playerWhoFinishedTheGame = playerId;
                return;
            }
        }
    }

    private void checkGameConditions() {
        checkConditions(winConditions, GameState.Win);
        checkConditions(lostConditions, GameState.Lost);
        if (checkIfGameIsFinished()) {
            logGameFinishedBy(playerWhoFinishedTheGame,gameState);
        }
    }

    @Override
    public synchronized void update() {
        if (checkIfGameIsFinished()) {
            return;
        }

        for (Timer timer:timers) {
            timer.update(this, sender);
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
        }
    }

    @Override
    public synchronized String getPlayerIDAvailable() {
        if (playerIDS.size() > 0 && indexPlayerIDAvailable < playerIDS.size()) {
            String playerId = playerIDS.get(indexPlayerIDAvailable);
            indexPlayerIDAvailable += 1;
            return playerId;
        }

        return null;
    }

    @Override
    public synchronized void setSender(Sender sender) {
        this.sender = sender;
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
