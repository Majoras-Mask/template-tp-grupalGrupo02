package ar.fiuba.tdd.tp.motor.chains.riddle;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.riddle.RiddleCommandHelp;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class ChainHelp extends ChainRiddle {

    public ChainHelp(GameRiddle game, String patternString) {
        super(game,patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new RiddleCommandHelp(this.riddle, "");
    }
}
