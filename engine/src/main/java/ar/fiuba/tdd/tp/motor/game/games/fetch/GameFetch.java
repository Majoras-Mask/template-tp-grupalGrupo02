package ar.fiuba.tdd.tp.motor.game.games.fetch;

import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.ComponentStick;

public class GameFetch implements Game {
    static final String stickName = "stick";
    static final String roomName = "room";
    ComponentRoom currentRoom;

    public GameFetch() {
        ComponentRoom room = new ComponentRoom(this.roomName);
        room.addComponent(new ComponentStick(this.stickName));
        this.currentRoom = room;
    }

    public boolean checkIfGameIsFinished() {
        //To win this game, you have to pick the stick
        return !this.currentRoom.hasComponent(this.stickName);
    }

    public void pickStick() {
        if (this.currentRoom.hasComponent(this.stickName)) {
            this.currentRoom.removeComponent(this.stickName);
        }
    }
}
