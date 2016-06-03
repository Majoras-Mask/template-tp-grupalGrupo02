package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class ObjectConcrete implements ObjectInterface {

    private HashMap<String, String> properties = new HashMap<>();
    private List<ObjectInterface> items = new ArrayList<>();
    private String description;

    public ObjectConcrete(String description) {
        this.description = description;
    }

    @Override
    public void add(ObjectInterface object) {
        if (!items.contains(object)) {
            items.add(object);
        }
    }

    @Override
    public void remove(ObjectInterface object) {
        if (items.contains(object)) {
            items.remove(object);
        }
    }

    @Override
    public void removeAll() {
        items.clear();
    }

    @Override
    public boolean hasObject(ObjectInterface object) {
        return items.contains(object);
    }

    @Override
    public List<ObjectInterface> getObjects() {
        return items;
    }

    @Override
    public int getSize() {
        return items.size();
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
