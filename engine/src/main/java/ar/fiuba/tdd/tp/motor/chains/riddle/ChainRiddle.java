package ar.fiuba.tdd.tp.motor.chains.riddle;

import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public abstract class ChainRiddle extends ChainCommandCreator {

    protected GameRiddle riddle;

    protected String getStringGroup(String message) {
        return UtilityParser.getGroup(this.getPatternString(), message, 1);
    }

    public ChainRiddle(GameRiddle game , String patternString) {
        riddle = game;
        loadPatternString(patternString);
    }
}
