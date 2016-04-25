package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.games.Game;

import java.util.LinkedList;
import java.util.List;

public abstract class ZorkTypeGame implements Game {
    public ComponentRoom currentRoom;
    public List<GameComponent> playerItems = new LinkedList<>();

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }

    public List<GameComponent> getPlayerItems() {
        return this.playerItems;
    }

    public boolean hasPlayerComponent(String id) {
        for (GameComponent component : this.playerItems) {
            if (component.getDescription().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addPlayerItem(GameComponent component) {
        this.playerItems.add(component);
    }

    public void removePlayerItem(GameComponent component) {
        this.playerItems.remove(component);
    }

    public void setCurrentRoom(ComponentRoom room) {
        this.currentRoom = room;
    }

    public void removeItemFromRoom(GameComponent component) {
        this.currentRoom.removeComponent(component.getDescription());
    }

    public void addItemToRoom(GameComponent component) {
        this.currentRoom.addComponent(component);
    }
}
