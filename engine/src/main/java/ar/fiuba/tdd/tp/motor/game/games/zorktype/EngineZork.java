package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.chains.zorktype.ChainZorkLookAround;
import ar.fiuba.tdd.tp.motor.chains.zorktype.ChainZorkOpen;
import ar.fiuba.tdd.tp.motor.chains.zorktype.ChainZorkPick;
import ar.fiuba.tdd.tp.motor.games.Engine;

public class EngineZork extends Engine {

    private ZorkTypeGame gameZork;

    private static String LOOKAROUND_PATTERN = "look around";
    private static String PICK_PATTERN = "pick";
    private static String OPEN_PATTERN = "open";
   // private static String CLOSE_PATTERN = "close";
    //private static String TALK_PATTERN = "talk";


    public EngineZork(ZorkTypeGame game) {
        this.gameZork = game;
    }


    @Override
    protected ChainCommandCreator createChain() {
        //TODO terminar

        ChainCommandCreator lookAround = new ChainZorkLookAround(this.gameZork, LOOKAROUND_PATTERN);
        ChainCommandCreator pick = new ChainZorkPick(this.gameZork, PICK_PATTERN);
        ChainCommandCreator open = new ChainZorkOpen(this.gameZork, OPEN_PATTERN);
       // ChainCommandCreator close;
       // ChainCommandCreator talk;

        lookAround.setNextChain(pick);
        pick.setNextChain(open);

        return lookAround;

    }
}
