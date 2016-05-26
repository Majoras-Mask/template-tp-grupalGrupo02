package ar.fiuba.tdd.tp.engine.player;

import ar.fiuba.tdd.tp.engine.component.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Inventory {

    private final List<Component> components = new ArrayList<>();
    private int limit = 10; //Default value

    public Inventory() {
    }

    public List<Component> getComponents() {
        return components;
    }

    public Integer getSize() {
        return this.components.size();
    }

    public void add(Component component) {
        this.components.add(component);
    }

    public void remove(Component component) {
        this.components.remove(component);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean limitReached() {
        return (getSize() >= limit);
    }
}
