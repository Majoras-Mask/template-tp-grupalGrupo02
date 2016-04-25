package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public abstract class GameComponentStoring extends GameComponents {
    protected List<GameComponents> components = new LinkedList<>();

    public GameComponentStoring() {
        super();
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

    public List<GameComponents> getListOfComponents() {
        return this.components;
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
}
