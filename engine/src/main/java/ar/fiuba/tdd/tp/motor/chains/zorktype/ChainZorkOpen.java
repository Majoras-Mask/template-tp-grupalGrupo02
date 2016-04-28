package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandOpen;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkOpen extends ChainZorkActionable {

    public ChainZorkOpen(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    public GameCommand getGameCommand(ZorkTypeGame game, String whatToOpen) {
        return new ZorkCommandOpen(this.gameZork, whatToOpen);
    }
}
