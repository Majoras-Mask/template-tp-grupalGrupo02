package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player implements PlayerInterface {
    private List<ComponentInterface> inventory = new LinkedList<>();
    private ComponentContainer room;
    //Esta lista va a ser necesaria para poder saber que comandos son soportados
    private Map<String, Behavior> actions = new HashMap<>();
    private static final String CANT_DO_ACTION = "Can't do that action.";

    public List<String> listOfWhatISee() {
        return room.listOfComponents();
    }

    public void setRoom(ComponentContainer room) {
        this.room = room;
    }

    public boolean playerHasItem(ComponentInterface item) {
        return inventory.contains(item);
    }

    public void addItemToInventory(ComponentInterface item) {
        inventory.add(item);
    }

    public boolean seesItemInRoom(ComponentInterface item) {
        return room.hasItem(item);
    }

    public boolean seesItemInRoom(String itemName) {
        return room.hasItem(itemName);
    }

    public void putItemInRoom(ComponentInterface item) {
        room.addItem(item);
    }

    public ComponentInterface obtainItemRoom(String itemName) {
        if (seesItemInRoom(itemName)) {
            return room.getItem(itemName);
        }
        return null;
    }

    public boolean removeItemFromRoom(ComponentInterface item) {
        if (seesItemInRoom(item)) {
            room.removeItem(item);
            return true;
        }
        return false;
    }

    public String currentRoomName() {
        return room.getName();
    }

    public void addBehavior(String actionString, Behavior behavior) {
        actions.put(actionString, behavior);
    }

    public String doCommand(String message) {
        //Lo que se va a tener que hacer aca es lo siguiente, le llega un mensaje, ver si ese
        //mensaje empieza con algun comando. Nosotros tenemos nuestros posibles comandos en la lista
        //possibleCommands. Si matchea, entonces vamos a tener que buscar ese objeto y si esta a la vista
        //decirle que realice esa accion
        for (String command : actions.keySet()) {
            Pattern commandPattern = Pattern.compile("^" + command);
            Matcher commandMatcher = commandPattern.matcher(message);
            if (commandMatcher.find()) {
                return actions.get(command).execute(message);
            }
        }
        return CANT_DO_ACTION;
    }
}
