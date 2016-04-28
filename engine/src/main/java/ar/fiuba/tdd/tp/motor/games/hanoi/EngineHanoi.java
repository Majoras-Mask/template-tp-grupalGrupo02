package ar.fiuba.tdd.tp.motor.games.hanoi;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainCheckSize;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainHanoiHelper;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainMove;
import ar.fiuba.tdd.tp.motor.games.Engine;
import ar.fiuba.tdd.tp.motor.games.Game;

public class EngineHanoi extends Engine {
    private GameHanoi gameHanoi;

    private static String HELPER_PATTERN = "what can i do with stack";
    private static String CHECKSIZE_PATTERN = "check top stack (\\d)";
    private static String MOVE_PATTERN = "move top stack (\\d) stack (\\d)";

    public EngineHanoi(GameHanoi gameHanoi) {
        super(gameHanoi);
        this.gameHanoi = gameHanoi;
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

    @Override
    protected String getWelcoming() {
        return this.gameHanoi.welcomeMessage();
    }
}
