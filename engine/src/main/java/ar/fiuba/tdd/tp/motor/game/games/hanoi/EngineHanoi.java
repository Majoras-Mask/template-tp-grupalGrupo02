package ar.fiuba.tdd.tp.motor.game.games.hanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainCheckSize;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainHanoi;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainHanoiHelper;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.ChainMove;

public class EngineHanoi extends Engine {
    private int defaultHanoiStack = 3;

    private GameHanoi gameHanoi;

    private static String HELPER_PATTERN = "what can i do with stack";
    private static String CHECKSIZE_PATTERN = "check top stack (\\d)";
    private static String MOVE_PATTERN = "move top stack (\\d) stack (\\d)";

    public EngineHanoi() {
        super();
        this.gameHanoi = new GameHanoi(defaultHanoiStack);
    }

    @Override
    protected ChainCommandCreator createChain() {
        ChainCommandCreator helper = new ChainHanoiHelper(gameHanoi, HELPER_PATTERN);
        ChainCommandCreator checkSize = new ChainCheckSize(gameHanoi, CHECKSIZE_PATTERN);
        ChainCommandCreator move = new ChainMove(gameHanoi, MOVE_PATTERN);

        helper.setNextChain(checkSize);
        checkSize.setNextChain(move);

        return helper;
    }
}
