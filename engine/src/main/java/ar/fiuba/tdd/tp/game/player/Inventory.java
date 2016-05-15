package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Inventory {

    private final List<Component> components;
    private final Integer limit;

    public Inventory(List<Component> components, Integer limit) {
        this.components = requireNonNull(components);
        this.limit = requireNonNull(limit);

        if (this.limitReached()) {
            throw new IllegalArgumentException("Limit exceeded!");
        }
    }

    public List<Component> getComponents() {
        return components;
    }

    public Integer getLimit() {
        return limit;
    }

    public void add(Component component) {
        if (this.limitReached()) {
            this.components.add(component);
        }
    }

    public void remove(Component component) {
        this.components.remove(component);
    }

    private Boolean limitReached() {
        return this.components.size() >= this. limit;
    }
}
