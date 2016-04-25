package ar.fiuba.tdd.tp.motor.chains.riddle;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;
import ar.fiuba.tdd.tp.motor.commands.riddle.RiddleCommandTake;

public class ChainTake extends ChainRiddle {

    public ChainTake(GameRiddle gameR, String patternString) {
        super(gameR, patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new RiddleCommandTake(this.riddle,getStringGroup(message));
    }
}
