package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public class ComponentRoom implements GameComponents {
    public List<GameComponents> components = new LinkedList<>();
    int id;
    private volatile int idCounter = 0;

    public ComponentRoom() {
        this.id = idCounter++;
    }

    public void addComponent(GameComponents component) {
        this.components.add(component);
    }

    public boolean hasComponent(String id) {
        for (GameComponents component : this.components) {
            if (component.getDescription().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void removeComponent(String id) {
        for (GameComponents component : this.components) {
            if (component.getDescription().equals(id)) {
                this.components.remove(component);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Door" + String.valueOf(this.id);
    }
}
