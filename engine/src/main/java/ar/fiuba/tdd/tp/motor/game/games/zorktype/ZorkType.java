package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;

public abstract class ZorkType implements Game {
    public ComponentRoom currentRoom;

    public String pick(String nameOfPickable) {

        GameComponents component = this.currentRoom.getComponent(nameOfPickable);
        if (component == null) {
            return "There is no such thing to pick.";
        }
        if (component.pick()) {
            this.currentRoom.removeComponent(nameOfPickable);
            return "Picked " + component.getDescription();
        } else {
            return "Can't pick " + component.getDescription();
        }
    }

    public String open(String nameOfOpenable) {
        return "opening";
    }

    public String close(String receiverOfAction) {
        return "closing";
    }
}
