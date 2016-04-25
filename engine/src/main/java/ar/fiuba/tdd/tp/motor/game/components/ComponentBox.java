package ar.fiuba.tdd.tp.motor.game.components;

import java.util.LinkedList;
import java.util.List;

public class ComponentBox extends GameComponentStoring {

    protected List<GameComponent> itemsIhad = new LinkedList<>();

    public ComponentBox() {
        super();
    }

    @Override
    public String getBasicName() {
        return "box";
    }

    @Override
    public Boolean open() {
        this.itemsIhad = getListOfComponents();
        for (GameComponent component: this.itemsIhad) {
            this.game.addItemToRoom(component);
            removeComponent(component.getDescription());
        }
        return true;
    }

    @Override
    public Boolean close() {
        for (GameComponent component: this.itemsIhad) {
            if (this.game.getCurrentRoom().hasComponent(component.getDescription())) {
                this.game.removeItemFromRoom(component);
                addComponent(component);
            }
        }
        return true;
    }
}
