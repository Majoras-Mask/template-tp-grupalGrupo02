package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.zorktype.*;
import ar.fiuba.tdd.tp.motor.games.Engine;

public class EngineZork extends Engine {

    private ZorkTypeGame gameZork;

    private static String LOOKAROUND_PATTERN = "look around";
    private static String PICK_PATTERN = "pick";
    private static String OPEN_PATTERN = "open";
    private static String CLOSE_PATTERN = "close";
    private static String TALK_PATTERN = "talk";


    public EngineZork(ZorkTypeGame game) {
        this.gameZork = game;
    }


    @Override
    protected ChainCommandCreator createChain() {
        //TODO terminar

        final ChainCommandCreator lookAround = new ChainZorkLookAround(this.gameZork, LOOKAROUND_PATTERN);
        final ChainCommandCreator pick = new ChainZorkPick(this.gameZork, PICK_PATTERN);
        final ChainCommandCreator open = new ChainZorkOpen(this.gameZork, OPEN_PATTERN);
        final ChainCommandCreator close = new ChainZorkClose(this.gameZork, CLOSE_PATTERN);
        final ChainCommandCreator talk = new ChainZorkTalk(this.gameZork,TALK_PATTERN);

        lookAround.setNextChain(pick);
        pick.setNextChain(open);
        open.setNextChain(close);
        close.setNextChain(talk);

        return lookAround;

    }
}
