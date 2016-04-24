package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.components.GameComponentsSimple;
import ar.fiuba.tdd.tp.motor.games.Game;

import java.util.LinkedList;
import java.util.List;

public abstract class ZorkTypeGame implements Game {
    public ComponentRoom currentRoom;
    public List<GameComponents> playerItems = new LinkedList<>();

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }

    public List<GameComponents> getPlayerItems() {
        return this.playerItems;
    }

    public void addPlayerItem(GameComponents component) {
        this.playerItems.add(component);
    }

    public void removePlayerItem(GameComponents component) {
        this.playerItems.remove(component);
    }

    public void setCurrentRoom(ComponentRoom room) {
        this.currentRoom = room;
    }

    public void removeItemFromRoom(GameComponents component) {
        this.currentRoom.removeComponent(component.getDescription());
    }
}
