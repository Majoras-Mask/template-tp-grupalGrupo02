package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Inventory {

    private final List<Component> components = new ArrayList<>();
    private final Integer limit;

    public Inventory(Integer limit) {
        this.limit = requireNonNull(limit);
    }

    public List<Component> getComponents() {
        return components;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSize() {
        return this.components.size();
    }

    public void add(Component component) {
        if (!this.limitReached()) {
            this.components.add(component);
        }
    }

    public void remove(Component component) {
        this.components.remove(component);
    }

    public Boolean limitReached() {
        return this.getSize() >= this.getLimit();
    }
}
