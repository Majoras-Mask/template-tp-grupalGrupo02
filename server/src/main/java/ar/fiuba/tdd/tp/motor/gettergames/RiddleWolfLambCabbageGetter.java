package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameRiddleWolfLambCabbage;

public class RiddleWolfLambCabbageGetter extends GameGetter {
    public RiddleWolfLambCabbageGetter() {
        this.nameOfGame = "Riddle";
        this.game = new GameRiddleWolfLambCabbage();
    }
}
