package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandLookAround;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkLookAround extends ChainZorkType {

    public ChainZorkLookAround(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new ZorkCommandLookAround(this.gameZork);
    }
}
