package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandClose;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkClose extends ChainZorkActionable {

    public ChainZorkClose(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    public GameCommand getGameCommand(ZorkTypeGame game, String whatToOpen) {
        return new ZorkCommandClose(this.gameZork, whatToOpen);
    }
}
