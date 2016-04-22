package ar.fiuba.tdd.tp.motor.game.games.fetch;

import ar.fiuba.tdd.tp.motor.Game;

public class GameFetch implements Game {
    boolean stickInPlayer;

    public GameFetch() {
        this.stickInPlayer = false;
    }

    public boolean checkIfGameIsFinished() {
        //To win this games, you have to pick the stick
        return stickInPlayer;
    }

    public boolean getStickInPlayer() {
        return this.stickInPlayer;
    }

    public void pickStick() {
        this.stickInPlayer = true;
    }
}
