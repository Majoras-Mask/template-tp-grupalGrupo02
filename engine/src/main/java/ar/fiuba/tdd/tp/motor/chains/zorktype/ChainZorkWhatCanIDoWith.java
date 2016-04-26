package ar.fiuba.tdd.tp.motor.chains.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandWhatCanIDoWith;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkWhatCanIDoWith extends ChainZorkActionable {

    public ChainZorkWhatCanIDoWith(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    public GameCommand getGameCommand(ZorkTypeGame game, String component) {
        return new ZorkCommandWhatCanIDoWith(this.gameZork, component);
    }
}
