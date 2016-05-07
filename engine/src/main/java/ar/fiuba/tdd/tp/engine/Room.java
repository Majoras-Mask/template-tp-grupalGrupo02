package ar.fiuba.tdd.tp.engine;

import java.util.*;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Room {
    private Map<String,ComponentInterface> items;
    private String name;
    private Door door;

    public Room(String name) {
        this.name = name;
        items = new HashMap<>();
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public void putItem(ComponentInterface item) {
        items.put(item.getName(), item);
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    public ComponentInterface getItem(String itemName) {
        ComponentInterface item = items.get(itemName);
        items.remove(itemName);
        return item;
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
