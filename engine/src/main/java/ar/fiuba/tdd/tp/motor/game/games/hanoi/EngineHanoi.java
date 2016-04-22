package ar.fiuba.tdd.tp.motor.game.games.hanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainCheckSize;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainMove;

public class EngineHanoi extends Engine {
    private int defaultHanoiStack = 3;

    public EngineHanoi() {
        this.game = new GameHanoi(defaultHanoiStack);
    }

    @Override
    public ChainCommandCreator getChainOfCommands(Game game) {
        ChainCommandCreator checkSize = new ChainCheckSize((GameHanoi)game);
        ChainCommandCreator mover = new ChainMove((GameHanoi)game);

        checkSize.setNextChain(mover);

        return checkSize;
    }
}
