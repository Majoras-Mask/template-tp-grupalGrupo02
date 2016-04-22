package ar.fiuba.tdd.tp.motor.games.hanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.games.hanoi.chainhanoi.ChainCheckSize;
import ar.fiuba.tdd.tp.motor.games.hanoi.chainhanoi.ChainHelper;
import ar.fiuba.tdd.tp.motor.games.hanoi.chainhanoi.ChainMove;

public class EngineHanoi implements Engine {
    Game game;
    private int defaultHanoiStack = 3;
    ChainCommandCreator chain;

    public EngineHanoi() {
        this.game = new GameHanoi(defaultHanoiStack);
        this.chain = getChainOfCommands(this.game);
    }

    private static ChainCommandCreator getChainOfCommands(Game game) {
        ChainCommandCreator checkSize = new ChainCheckSize((GameHanoi)game);
        ChainCommandCreator mover = new ChainMove((GameHanoi)game);

        checkSize.setNextChain(mover);

        return checkSize;
    }

    @Override
    public String request(String message) {
        return chain.createCommand(message).execute();
    }
}
