package ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoor;

import ar.fiuba.tdd.tp.motor.game.components.ComponentKey;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class GameOpenDoor extends ZorkTypeGame {

    public GameOpenDoor() {
        ComponentRoom room = new ComponentRoom();
        ComponentKey key = new ComponentKey();
        room.addComponent(key);
        this.currentRoom = room;
    }


    @Override
    public boolean checkIfGameIsFinished() {
        return false;
    }
}
