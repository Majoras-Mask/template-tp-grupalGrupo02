package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

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
