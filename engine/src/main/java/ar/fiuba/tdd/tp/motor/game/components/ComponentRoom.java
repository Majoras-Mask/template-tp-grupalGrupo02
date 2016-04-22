package ar.fiuba.tdd.tp.motor.game.components;

import java.util.Collection;

public class ComponentRoom implements GameComponents {
    private Collection<GameComponents> components;

    public ComponentRoom(Collection<GameComponents> components) {
        this.components = components;
    }

    public void addComponent(GameComponents component) {
        this.components.add(component);
    }
}
