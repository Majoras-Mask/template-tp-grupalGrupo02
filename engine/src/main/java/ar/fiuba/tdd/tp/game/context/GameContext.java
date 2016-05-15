package ar.fiuba.tdd.tp.game.context;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;
import java.util.Optional;

/**
 * A {@link GameContext} represents the components for a certain character in a certain moment.
 * It can be a boat, a room, a custom scenario, etc...
 */
public class GameContext {

    private final String name;
    private final List<Component> components;

    public GameContext(String name, List<Component> components) {
        this.name = name;
        this.components = components;
    }

    public Optional<Component> findComponentByName(String name) {
        return this.components.stream().filter(component -> component.getName().equals(name)).findFirst();
    }

    public String getName() {
        return name;
    }

    public List<Component> getComponents() {
        return components;
    }


    public void add(Component component) {
        this.components.add(component);
    }

    public void remove(Component component) {
        this.components.remove(component);
    }

}
