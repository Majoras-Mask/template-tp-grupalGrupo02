package ar.fiuba.tdd.tp.motor.chains.zorktype;


import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.zorktype.ZorkCommandPick;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ChainZorkPick extends ChainZorkType {

    public ChainZorkPick(ZorkTypeGame game, String patternString) {
        super(game, patternString);
    }

    private String getWhatToPickFromMessage(String message) {
        return UtilityParser.getGroup(this.getPatternString(), message, 1);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        String whatToPick = getWhatToPickFromMessage(message);
        return new ZorkCommandPick(this.gameZork, whatToPick);
    }


}
