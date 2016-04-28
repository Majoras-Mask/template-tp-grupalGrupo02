package ar.fiuba.tdd.tp.motor.games.riddle;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainCross;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainHelp;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainLeave;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainTake;
import ar.fiuba.tdd.tp.motor.games.Engine;

public class EngineRiddle extends Engine{

    private GameRiddle gameRiddle;

    private static String TAKE = "take (sheep|wolf|cabbage)";

    private static String LEAVE = "leave (sheep|wolf|cabbage)";

    private static String CROSS = "cross (south|north)-shore";

    private static String HELP = "help";

    public EngineRiddle(GameRiddle game) {
        super(game);
        gameRiddle = game;
    }

    private void connection(ChainCommandCreator chTake, ChainCommandCreator leave, ChainCommandCreator cross, ChainCommandCreator help) {
        leave.setNextChain(cross);
        chTake.setNextChain(leave);
        cross.setNextChain(help);
    }

    @Override
    protected ChainCommandCreator createChain() {
        ChainCommandCreator chainTake = new ChainTake(gameRiddle,TAKE);
        ChainCommandCreator chainLeave = new ChainLeave(gameRiddle,LEAVE);
        ChainCommandCreator chainCross = new ChainCross(gameRiddle, CROSS);
        ChainCommandCreator chainHelp = new ChainHelp(gameRiddle, HELP);

        connection(chainTake,chainLeave,chainCross,chainHelp);

        return chainTake;
    }

    @Override
    public String getWelcoming() {
        return this.gameRiddle.welcomeMessage();
    }
}
