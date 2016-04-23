package ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.ComponentStick;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class GameFetch extends ZorkTypeGame {
    private String stickName;

    public GameFetch() {
        ComponentRoom room = new ComponentRoom();
        ComponentStick stick = new ComponentStick();
        this.stickName = stick.getDescription();
        room.addComponent(stick);
        this.currentRoom = room;
    }

    public boolean checkIfGameIsFinished() {
        //To win this game, you have to pick the stick
        return !this.currentRoom.hasComponent(this.stickName);
    }
}
