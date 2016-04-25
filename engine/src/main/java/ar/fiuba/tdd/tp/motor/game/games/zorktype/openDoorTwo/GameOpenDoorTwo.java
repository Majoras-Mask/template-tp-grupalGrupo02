package ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoortwo;

import ar.fiuba.tdd.tp.motor.game.components.ComponentBox;
import ar.fiuba.tdd.tp.motor.game.components.ComponentKey;
import ar.fiuba.tdd.tp.motor.game.components.ComponentNormalDoor;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class GameOpenDoorTwo extends ZorkTypeGame {

    private ComponentRoom winningRoom;

    public GameOpenDoorTwo() {
        ComponentRoom roomOne = new ComponentRoom();
        this.winningRoom = new ComponentRoom();
        ComponentBox box = new ComponentBox();
        ComponentKey key = new ComponentKey();
        box.addComponent(key);
        roomOne.addComponent(box);
        ComponentNormalDoor door = new ComponentNormalDoor(this, this.winningRoom, key);
        roomOne.addComponent(door);
        this.currentRoom = roomOne;
    }

    @Override
    public boolean checkIfGameIsFinished() {
        return (getCurrentRoom() == this.winningRoom);
    }
}
