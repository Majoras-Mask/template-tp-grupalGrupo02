package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.chains.zorktype.ChainZorkType;
import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ChainZorkActionable extends ChainZorkType {

    public ChainZorkActionable(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    public abstract GameCommand getGameCommand(ZorkTypeGame game, String actionWith);

    @Override
    protected GameCommand factoryCommand(String message) {
        String whatToActionWith = getWhoToDoActionWith(message);
        return getGameCommand(this.gameZork, whatToActionWith);
    }
}
