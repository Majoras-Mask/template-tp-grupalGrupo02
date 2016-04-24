package ar.fiuba.tdd.tp.motor.chains.hanoi;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.GameCommandHelper;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;

/**
 * Created by kevin on 22/04/16.
 */
public class ChainHanoiHelper extends ChainHanoi{

    public ChainHanoiHelper(GameHanoi game, String patternString) {
        super(game,patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        return new GameCommandHelper("You can check top/move top");
    }

}
