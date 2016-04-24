package ar.fiuba.tdd.tp.motor.chains.zorktype;


import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandPick;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkPick extends ChainZorkActionable {

    public ChainZorkPick(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    public GameCommand getGameCommand(ZorkTypeGame game, String whatToPick) {
        return new ZorkCommandPick(this.gameZork, whatToPick);
    }
}
