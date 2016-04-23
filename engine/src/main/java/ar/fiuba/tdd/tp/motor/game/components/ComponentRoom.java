package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public class ComponentRoom implements GameComponents {
    public List<GameComponents> components = new LinkedList<>();
    String id;

    public ComponentRoom(String id) {
        this.id = id;
    }

    public void addComponent(GameComponents component) {
        this.components.add(component);
    }

    public boolean hasComponent(String id) {
        for (GameComponents component : this.components) {
            if (component.id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void removeComponent(String id) {
        for (GameComponents component : this.components) {
            if (component.id().equals(id)) {
                this.components.remove(component);
            }
        }
    }

    @Override
    public String id() {
        return this.id;
    }
}
