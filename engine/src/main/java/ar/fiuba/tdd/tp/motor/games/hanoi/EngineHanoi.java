package ar.fiuba.tdd.tp.motor.games.hanoi;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainCheckSize;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainHanoiHelp;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainHanoiHelper;
import ar.fiuba.tdd.tp.motor.chains.hanoi.ChainMove;
import ar.fiuba.tdd.tp.motor.games.Engine;
import ar.fiuba.tdd.tp.motor.games.Game;

public class EngineHanoi extends Engine {
    private GameHanoi gameHanoi;

    private static String HELPER_PATTERN = "what can i do with stack";
    private static String CHECKSIZE_PATTERN = "check top stack (\\d)";
    private static String MOVE_PATTERN = "move top stack (\\d) stack (\\d)";
    private static String HELP_PATTERN = "help";

    public EngineHanoi(GameHanoi gameHanoi) {
        super(gameHanoi);
        this.gameHanoi = gameHanoi;
    }

    @Override
    protected ChainCommandCreator createChain() {
        final ChainCommandCreator helper = new ChainHanoiHelper(gameHanoi, HELPER_PATTERN);
        final ChainCommandCreator checkSize = new ChainCheckSize(gameHanoi, CHECKSIZE_PATTERN);
        final ChainCommandCreator move = new ChainMove(gameHanoi, MOVE_PATTERN);
        final ChainCommandCreator help = new ChainHanoiHelp(gameHanoi,HELP_PATTERN);

        helper.setNextChain(checkSize);
        checkSize.setNextChain(move);
        move.setNextChain(help);

        return helper;
    }

    @Override
    public String getWelcomeMessage() {
        return this.gameHanoi.welcomeMessage();
    }
}
