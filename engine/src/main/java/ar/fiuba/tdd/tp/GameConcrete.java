package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditionelements.ConditionElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 29/05/16.
 */
public class GameConcrete implements Game, Context, Logger {

    private List<ObjectInterface> objects = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private HashMap<String, String> map = new HashMap<String,String>();
    private StringBuilder buffer = new StringBuilder();

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
    public void executeCommand(String playerName, String commandString) {
        setUpHashMap(playerName);

        for (Command command: commands) {
            if (command.matches(commandString)) {
                command.execute(commandString, this);
            }
        }

        log("Can't do that.");
    }

    @Override
    public void update() {

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

    @Override
    public void log(String string) {
        buffer.append(string);
    }

    @Override
    public String getLog() {
        String messageToReturn = buffer.toString();
        buffer.setLength(0);
        return messageToReturn;
    }
}
