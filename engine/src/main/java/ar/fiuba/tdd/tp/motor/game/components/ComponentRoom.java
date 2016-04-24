package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public class ComponentRoom extends GameComponents {
    public List<GameComponents> components = new LinkedList<>();

    public ComponentRoom() {
        this.componentName = "Room";
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

    public GameComponents getComponent(String whatToGet) {
        for (GameComponents component : this.components) {
            if (component.getDescription().equals(whatToGet)) {
                return this.components.get(this.components.indexOf(component));
            }
        }
        return null;
    }

    @Override
    public Boolean pick() {
        return false;
    }

    @Override
    public Boolean close() {
        return false;
    }

    @Override
    public Boolean open() {
        return false;
    }

    @Override
    public Boolean talk() {
        return false;
    }


}
