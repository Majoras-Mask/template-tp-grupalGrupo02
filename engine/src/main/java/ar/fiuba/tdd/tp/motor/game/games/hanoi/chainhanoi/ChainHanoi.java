package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;

public abstract class ChainHanoi extends ChainCommandCreator {

    protected GameHanoi gameHanoi;

    public ChainHanoi(GameHanoi gameHanoi, String patternString) {
        this.gameHanoi = gameHanoi;
        loadPatternString(patternString);
    }

}
