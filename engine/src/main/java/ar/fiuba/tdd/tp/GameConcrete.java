package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditionelements.ConditionElement;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.timer.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 29/05/16.
 */
public class GameConcrete implements Game, Context {

    private List<ObjectInterface> objects = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<String,String>();
    private GameState gameState = GameState.Running;
    private List<Timer> timers = new ArrayList<>();
    private Condition winCondition;

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
        if (winCondition.check(this)) {
            return "Game State Won";
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
        for (Timer timer:timers) {
            timer.update();
        }

        clearFinishedTimers();
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
    public void setWinCondition(Condition winCondition) {
        this.winCondition = winCondition;
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
