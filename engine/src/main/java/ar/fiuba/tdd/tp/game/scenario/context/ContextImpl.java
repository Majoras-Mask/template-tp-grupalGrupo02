package ar.fiuba.tdd.tp.game.scenario.context;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;
import java.util.Optional;

public class ContextImpl implements Context {

    private final String name;
    private final List<Component> components;

    public ContextImpl(String name, List<Component> components) {
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
