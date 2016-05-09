package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.*;

public class Room {
    private Map<String, ComponentInterface> items;
    private String name;
    private Door door;

    public Room(String name) {
        this.name = name;
        items = new HashMap<>();
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public void addItem(ComponentInterface item) {
        items.put(item.getName(), item);
    }

    public ComponentInterface getItem(String itemName) {
        if (hasItem(itemName)) {
            return items.get(itemName);
        }
        return null;
    }

    public boolean hasItem(ComponentInterface item) {
        return items.containsKey(item.getName());
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    public ComponentInterface removeItem(String itemName) {
        if (hasItem(itemName)) {
            ComponentInterface itemToReturn = items.get(itemName);
            items.remove(itemName);
            return itemToReturn;
        }
        return null;
    }

    public ComponentInterface removeItem(ComponentInterface item) {
        if (hasItem(item.getName())) {
            return removeItem(item.getName());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getItemList() {
        StringBuilder stringBuilder = new StringBuilder();
        items.forEach((itemName, item) -> stringBuilder.append(" a " + itemName));
        return stringBuilder.toString();
    }

    public Room doTransaction() {
        return this.door.getOtherRoom(this);
    }
}
