package ar.fiuba.tdd.tp.motor.game.games.fetch;

import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.ComponentStick;

public class GameFetch implements Game {
    private String stickName;
    ComponentRoom currentRoom;

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

    public void pickStick() {
        if (this.currentRoom.hasComponent(this.stickName)) {
            this.currentRoom.removeComponent(this.stickName);
        }
    }
}
