package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player implements PlayerInterface {
    private List<ComponentInterface> items = new LinkedList<>();
    private Room room;
    //Esta lista va a ser necesaria para poder saber que comandos son soportados
    protected List<String> possibleCommands;
    private Map<String, Behavior> actions = new HashMap<>();
    private static final String CANT_DO_ACTION = "Can't do that action.";
    private static final String NO_ITEM_IN_ROOM = "There is no such thing in this room.";

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean playerHasItem(ComponentInterface item) {
        return items.contains(item);
    }

    public void addItemToInventory(ComponentInterface item) {
        items.add(item);
    }

    public boolean seesItemInRoom(ComponentInterface item) {
        return room.hasItem(item);
    }

    public boolean removeItem(ComponentInterface item) {
        if (seesItemInRoom(item)) {
            room.removeItem(item);
            return true;
        }
        return false;
    }

    public boolean isInRoom(Room room) {
        return room.equals(this.room);
    }

    public void setListOfPossibleCommands(List<String> list) {
        possibleCommands = list;
    }

    public void addBehavior(String actionString, Behavior behavior) {
        actions.put(actionString, behavior);
    }

    private boolean haveThatCommand(String command) {
        return actions.containsKey(command);
    }

    private String makeComponentDoAction(String command, String whereToApply) {
        if (room.hasItem(whereToApply)) {
            ComponentInterface item = room.removeItem(whereToApply);
            return item.doAction(command, null);
        }
        return NO_ITEM_IN_ROOM;
    }

    private String playerOrComponentAction(String command, String whereToApply) {
        if (haveThatCommand(command)) {
            return actions.get(command).execute(null);
        }
        //If I can't do it, it's something that I have to do with a component(item)
        //TODO ojo que whereToApply tiene todo lo que le sigue al command (puede tener mas que un item)
        return makeComponentDoAction(command, whereToApply);
    }

    public String doCommand(String message) {
        //Lo que se va a tener que hacer aca es lo siguiente, le llega un mensaje, ver si ese
        //mensaje empieza con algun comando. Nosotros tenemos nuestros posibles comandos en la lista
        //possibleCommands. Si matchea, entonces vamos a tener que buscar ese objeto y si esta a la vista
        //decirle que realice esa accion
        for (String command : possibleCommands) {
            Pattern commandPattern = Pattern.compile(command + ".*");
            Matcher commandMatcher = commandPattern.matcher(message);
            if (commandMatcher.find()) {
                //TODO revisar si esto da lo esperado que es todo menos el comando
                return playerOrComponentAction(command, commandMatcher.group(1));
            }
        }
        return CANT_DO_ACTION;
    }


}
