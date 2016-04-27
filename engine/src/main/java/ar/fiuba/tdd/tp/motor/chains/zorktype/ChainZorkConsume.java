package ar.fiuba.tdd.tp.motor.chains.zorktype;


import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandConsume;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkConsume extends ChainZorkActionable {

    public ChainZorkConsume(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    @Override
    public GameCommand getGameCommand(ZorkTypeGame game, String whatToConsume) {
        return new ZorkCommandConsume(this.gameZork, whatToConsume);
    }
}
