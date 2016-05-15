package ar.fiuba.tdd.tp.game.context;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;
import java.util.Optional;

/**
 * A {@link GameContextImpl} represents the components for a certain character in a certain moment.
 * It can be a boat, a room, a custom scenario, etc...
 */
public class GameContextImpl implements GameContext {

    private final String name;
    private final List<Component> components;

    public GameContextImpl(String name, List<Component> components) {
        this.name = name;
        this.components = components;
    }

    @Override
    public Optional<Component> findComponentByName(String name) {
        return this.components.stream().filter(component -> component.getName().equals(name)).findFirst();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public void add(Component component) {
        this.components.add(component);
    }

    @Override
    public void remove(Component component) {
        this.components.remove(component);
    }

}
