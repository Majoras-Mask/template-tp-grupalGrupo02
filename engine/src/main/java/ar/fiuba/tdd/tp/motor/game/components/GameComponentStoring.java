package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public abstract class GameComponentStoring extends GameComponent {
    protected List<GameComponent> components = new LinkedList<>();

    public GameComponentStoring() {
        super();
    }

    public void addComponent(GameComponent component) {
        this.components.add(component);
    }

    public boolean hasComponent(String id) {
        for (GameComponent component : this.components) {
            if (component.getDescription().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<GameComponent> getListOfComponents() {
        return this.components;
    }

    public void removeComponent(String id) {
        for (GameComponent component : this.components) {
            if (component.getDescription().equals(id)) {
                this.components.remove(component);
            }
        }
    }

    public GameComponent getComponent(String whatToGet) {
        for (GameComponent component : this.components) {
            if (component.getDescription().equals(whatToGet)) {
                return this.components.get(this.components.indexOf(component));
            }
        }
        return null;
    }
}
