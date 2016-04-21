package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameOpenDoor;

public class OpenDoorGetter extends GameGetter {
    public OpenDoorGetter() {
        this.game = new GameOpenDoor();
    }
}
