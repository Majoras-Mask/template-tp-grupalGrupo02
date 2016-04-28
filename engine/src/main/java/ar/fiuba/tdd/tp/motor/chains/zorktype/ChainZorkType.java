package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ChainZorkType extends ChainCommandCreator {
    protected ZorkTypeGame gameZork;

    public ChainZorkType(ZorkTypeGame game, String patternString) {
        this.gameZork = game;
        loadPatternString(patternString);
    }

    public String getWhoToDoActionWith(String message) {
        return UtilityParser.getGroup(this.getPatternString(), message, 1);
    }
}
