package ar.fiuba.tdd.tp.motor.chains.hanoi;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;

public abstract class ChainHanoi extends ChainCommandCreator {

    protected GameHanoi gameHanoi;

    public ChainHanoi(GameHanoi gameHanoi, String patternString) {
        this.gameHanoi = gameHanoi;
        loadPatternString(patternString);
    }

}
