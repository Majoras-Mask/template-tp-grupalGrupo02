package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.GameCommandHelper;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;

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
