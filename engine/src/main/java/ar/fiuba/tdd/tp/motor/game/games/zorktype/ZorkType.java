package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;

public abstract class ZorkType implements Game {
    public ComponentRoom currentRoom;

    public String pick(String nameOfPickable) {
        if (this.currentRoom.hasComponent(nameOfPickable)) {
            this.currentRoom.removeComponent(nameOfPickable);
            return ("You picked " + nameOfPickable + ".");
        } else {
            return "There is no such thing to pick.";
        }
    }
}
