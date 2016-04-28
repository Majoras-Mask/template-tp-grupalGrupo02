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
        return new GameCommandHelper("GAME COMMANDS\n What can i do with stack X, check top stack X, move top stack X stack Y");
    }
}
