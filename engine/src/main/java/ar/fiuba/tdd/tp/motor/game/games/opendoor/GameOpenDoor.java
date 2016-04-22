package ar.fiuba.tdd.tp.motor.game.games.opendoor;

import ar.fiuba.tdd.tp.motor.Game;

public class GameOpenDoor implements Game {

    private boolean doorOpen;
    private boolean playerHasKey;

    public GameOpenDoor() {
        this.doorOpen = false;
        this.playerHasKey = false;
    }

    public void pickKey() {
        this.playerHasKey = true;
    }

    public void openDoor() {
        if (playerHasKey) {
            this.doorOpen = true;
        }
    }

    public boolean checkIfGameIsFinished() {
        //Game is won if door is opened
        return this.doorOpen;
    }

    public boolean getPlayerHasKey() {
        return this.playerHasKey;
    }

    public boolean getDoorOpen() {
        return this.doorOpen;
    }
}
