package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameOpenDoorTwo;

public class OpenDoorTwoGetter extends GameGetter{
    public OpenDoorTwoGetter() {
        this.nameOfGame = "Open Door 2";
        this.game = new GameOpenDoorTwo();
    }
}
