package ar.fiuba.tdd.tp.engine.gamecomponents;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ComponentContainer extends ComponentSimple {

    private Map<String, ComponentInterface> items = new HashMap<>();

    public ComponentContainer(String name) {
        super(name);
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

    public int itemCount() {
        return items.size();
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

    public List<String> listOfComponents() {
        List<String> list = new LinkedList<>();
        for (String component : items.keySet()) {
            list.add(component);
        }
        return list;
    }
}
