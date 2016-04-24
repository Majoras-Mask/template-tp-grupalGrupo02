package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.games.Game;

public abstract class ZorkTypeGame implements Game {
    public ComponentRoom currentRoom;

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }
}
