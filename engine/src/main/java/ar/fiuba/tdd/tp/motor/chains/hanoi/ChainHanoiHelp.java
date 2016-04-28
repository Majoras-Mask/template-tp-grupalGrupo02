package ar.fiuba.tdd.tp.motor.chains.hanoi;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.GameCommandHelper;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;

public class ChainHanoiHelp extends ChainHanoi {

    public ChainHanoiHelp(GameHanoi game, String patternString) {
        super(game,patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        //TODO mejorar esto
        return new GameCommandHelper("GAME COMMANDS\n check X, move X");
    }
}
