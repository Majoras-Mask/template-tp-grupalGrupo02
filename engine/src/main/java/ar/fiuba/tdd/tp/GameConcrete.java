package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditionelements.ConditionElement;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.timer.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 29/05/16.
 */
public class GameConcrete implements Game, Context {

    private List<ObjectInterface> objects = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<String,String>();
    private GameState gameState = GameState.Running;
    private List<Timer> timers = new ArrayList<>();
    private HashMap<String, Condition> winConditions = new HashMap<>();
    private HashMap<String, Condition> lostConditions = new HashMap<>();
    private List<String> playerIDS = new ArrayList<>();
    private int indexPlayerIDAvailable = 0;
    //private String playerWhoFinishedTheGame;

    @Override
    public void addObject(ObjectInterface object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

    @Override
    public void addCommand(Command command) {
        if (!commands.contains(command)) {
            commands.add(command);
        }
    }

    private void setUpHashMap(String playerName) {
        map.put("(player)", playerName);
    }

    @Override
    public String executeCommand(String playerName, String commandString) {
        if (getGameState() == GameState.Win  || getGameState() == GameState.Lost) {
            return "Game finished";
        }

        setUpHashMap(playerName);

        for (Command command: commands) {
            if (command.matches(commandString)) {
                return command.execute(commandString, this);
            }
        }

        return "No command found.";

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

    @Override
    public void update() {

        checkConditions(winConditions, GameState.Win);
        checkConditions(lostConditions, GameState.Lost);

        for (Timer timer:timers) {
            timer.update();
        }

        clearFinishedTimers();
    }

    private void checkConditions(HashMap<String, Condition> conditions, GameState gameStateToSet) {
        for (Map.Entry<String, Condition> entry : conditions.entrySet()) {
            String playerId = entry.getKey();
            Condition condition = entry.getValue();
            if (condition.check(this)) {
                setGameState(gameStateToSet);
            }
        }
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void addTimer(Timer timer) {
        this.timers.add(timer);
    }

    @Override
    public void setWinCondition(String playerID, Condition winCondition) {
        this.winConditions.put(playerID, winCondition);
    }

    @Override
    public void setLostCondition(String playerID, Condition lostCondition) {
        this.lostConditions.put(playerID, lostCondition);
    }

    @Override
    public void addPlayer(ObjectInterface player) {
        addObject(player);
        if (!playerIDS.contains(player.getDescription())) {
            playerIDS.add(player.getDescription());
        }
    }

    @Override
    public String getPlayerIDAvailable() {
        if (playerIDS.size() > 0 && indexPlayerIDAvailable < playerIDS.size()) {
            String playerId = playerIDS.get(indexPlayerIDAvailable);
            indexPlayerIDAvailable += 1;
            return playerId;
        }

        return null;
    }

    @Override
    public ObjectInterface getObject(String name) {
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
