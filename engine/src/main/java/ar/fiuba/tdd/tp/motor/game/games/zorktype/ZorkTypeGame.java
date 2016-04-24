package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponentsSimple;
import ar.fiuba.tdd.tp.motor.games.Game;

import java.util.LinkedList;
import java.util.List;

public abstract class ZorkTypeGame implements Game {
    public ComponentRoom currentRoom;
    public List<GameComponentsSimple> playerItems = new LinkedList<>();

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }

    public List<GameComponentsSimple> getPlayerItems() {
        return this.playerItems;
    }

    public void setCurrentRoom(ComponentRoom room) {
        this.currentRoom = room;
    }
}
