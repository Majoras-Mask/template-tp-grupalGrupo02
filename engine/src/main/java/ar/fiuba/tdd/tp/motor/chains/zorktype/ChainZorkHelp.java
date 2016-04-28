package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandHelp;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkHelp extends ChainZorkType {

    public ChainZorkHelp(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new ZorkCommandHelp(this.gameZork);
    }
}
