package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class ObjectConcrete implements Container, Descriptible {

    private HashMap<String, String> properties = new HashMap<>();
    private List<String> items = new ArrayList<>();
    private String description;

    ObjectConcrete(String description) {
        this.description = description;
    }

    @Override
    public void add(String value) {
        if (!items.contains(value)) {
            items.add(value);
        }
    }

    @Override
    public void remove(String value) {
        if (items.contains(value)) {
            items.remove(value);
        }
    }

    @Override
    public void removeAll() {
        items.clear();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setProperty(String property, String value) {
        properties.put(property,value);
    }

    @Override
    public String getProperty(String property) {
        return properties.get(property);
    }
}
