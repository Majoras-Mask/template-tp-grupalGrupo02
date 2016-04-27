package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandStore;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkStore extends ChainZorkType {

    public ChainZorkStore(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        String whatToStore = UtilityParser.getGroup(this.getPatternString(), message, 1);
        String whereToStore = UtilityParser.getGroup(this.getPatternString(), message, 2);
        return new ZorkCommandStore(this.gameZork, whatToStore, whereToStore);
    }
}
