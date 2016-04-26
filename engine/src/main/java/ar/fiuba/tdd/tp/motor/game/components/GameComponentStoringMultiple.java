package ar.fiuba.tdd.tp.motor.game.components;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

import java.util.LinkedList;
import java.util.List;

public abstract class GameComponentStoringMultiple extends GameComponentStoring {

    protected List<GameComponent> components = new LinkedList<>();
    protected List<GameComponent> itemsIhad = new LinkedList<>();

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

    @Override
    public Boolean open(ZorkTypeGame game) {
        this.itemsIhad = getListOfComponents();
        for (GameComponent component: this.itemsIhad) {
            game.addItemToRoom(component);
            removeComponent(component.getDescription());
        }
        return true;
    }

    @Override
    public Boolean close(ZorkTypeGame game) {
        for (GameComponent component: this.itemsIhad) {
            if (game.getCurrentRoom().hasComponent(component.getDescription())) {
                game.removeItemFromRoom(component);
                addComponent(component);
            }
        }
        return true;
    }
}
