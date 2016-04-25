package ar.fiuba.tdd.tp.motor.chains.riddle;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;
import ar.fiuba.tdd.tp.motor.commands.riddle.RiddleCommandCross;

public class ChainCross extends ChainRiddle {

    public ChainCross(GameRiddle game, String patternString) {
        super(game,patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new RiddleCommandCross(this.riddle,getStringGroup(message));
    }
}
