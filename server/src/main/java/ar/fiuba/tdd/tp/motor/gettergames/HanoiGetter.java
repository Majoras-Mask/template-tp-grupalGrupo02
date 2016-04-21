package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameHanoi;

public class HanoiGetter extends GameGetter{
    public HanoiGetter() {
        this.nameOfGame = "Hanoi";
        this.game = new GameHanoi();
    }
}
