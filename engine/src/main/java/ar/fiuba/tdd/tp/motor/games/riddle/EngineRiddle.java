package ar.fiuba.tdd.tp.motor.games.riddle;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainCross;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainLeave;
import ar.fiuba.tdd.tp.motor.chains.riddle.ChainTake;
import ar.fiuba.tdd.tp.motor.games.Engine;

public class EngineRiddle extends Engine{

    private GameRiddle gameRiddle;

    private static String TAKE = "take (sheep|wolf|cabbage)";

    private static String LEAVE = "leave (sheep|wolf|cabbage)";

    private static String CROSS = "cross (south|north)-shore";

    public EngineRiddle(GameRiddle game) {
        super(game);
        gameRiddle = game;
    }

    private void connection(ChainCommandCreator chTake, ChainCommandCreator chLeave, ChainCommandCreator chCross ) {
        chLeave.setNextChain(chCross);
        chTake.setNextChain(chLeave);
    }

    @Override
    protected ChainCommandCreator createChain() {
        ChainCommandCreator chainTake = new ChainTake(gameRiddle,TAKE);
        ChainCommandCreator chainLeave = new ChainLeave(gameRiddle,LEAVE);
        ChainCommandCreator chainCross = new ChainCross(gameRiddle, CROSS);

        connection(chainTake,chainLeave,chainCross);

        return chainTake;
    }

    @Override
    protected String getWelcoming() {
        return this.gameRiddle.welcomeMessage();
    }
}
