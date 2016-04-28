package ar.fiuba.tdd.tp.motor.chains.riddle;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.riddle.RiddleCommandLeave;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class ChainLeave extends ChainRiddle{

    public ChainLeave(GameRiddle game, String patternString) {
        super(game,patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new RiddleCommandLeave(this.riddle, getStringGroup(message));
    }
}
