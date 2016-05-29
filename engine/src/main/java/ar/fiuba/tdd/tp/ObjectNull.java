package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 29/05/16.
 */
public class ObjectNull implements ObjectInterface {

    private static ObjectNull instance;

    public static ObjectNull getInstance() {
        if (instance == null) {
            instance = new ObjectNull();
        }

        return instance;
    }

    private ObjectNull() {

    }

    @Override
    public void add(ObjectInterface object) {
        return;
    }

    @Override
    public void remove(ObjectInterface object) {
        return;
    }

    @Override
    public void removeAll() {
        return;
    }

    @Override
    public boolean hasObject(ObjectInterface object) {
        return false;
    }

    @Override
    public List<ObjectInterface> getObjects() {
        return new ArrayList<ObjectInterface>();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setProperty(String property, String value) {
        return;
    }

    @Override
    public String getProperty(String property) {
        return "";
    }

    @Override
    public List<ObjectInterface> getRelatedObjects() {
        return new ArrayList<ObjectInterface>();
    }

    @Override
    public void addRelatedObject(ObjectInterface object) {
        return;
    }
}
