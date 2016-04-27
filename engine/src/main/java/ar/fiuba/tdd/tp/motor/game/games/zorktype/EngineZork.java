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
    private static String WHATCANIDO_PATTERN = "what can i do with";
    private static String STOREITEM_PATTERN = "store (.*) in (.*)";
    private static String CONSUME_PATTERN = "consume";


    public EngineZork(ZorkTypeGame game) {
        this.gameZork = game;
    }


    @Override
    public String request(String message) {
        if (this.chain == null) {
            this.chain = this.createChain();
        }
        String response = this.chain.createCommand(message).execute();

        if (this.gameZork.checkIfGameIsFinished()) {
            return "You won the game";
        }
        return response;
    }


    @Override
    protected ChainCommandCreator createChain() {

        final ChainCommandCreator lookAround = new ChainZorkLookAround(this.gameZork, LOOKAROUND_PATTERN);
        final ChainCommandCreator pick = new ChainZorkPick(this.gameZork, PICK_PATTERN);
        final ChainCommandCreator open = new ChainZorkOpen(this.gameZork, OPEN_PATTERN);
        final ChainCommandCreator close = new ChainZorkClose(this.gameZork, CLOSE_PATTERN);
        final ChainCommandCreator talk = new ChainZorkTalk(this.gameZork,TALK_PATTERN);
        final ChainCommandCreator whatCanIDoWith = new ChainZorkWhatCanIDoWith(this.gameZork, WHATCANIDO_PATTERN);
        final ChainCommandCreator store = new ChainZorkStore(this.gameZork, STOREITEM_PATTERN);

        lookAround.setNextChain(pick);
        pick.setNextChain(open);
        open.setNextChain(close);
        close.setNextChain(talk);
        talk.setNextChain(whatCanIDoWith);
        whatCanIDoWith.setNextChain(store);
        final ChainCommandCreator consume = new ChainZorkConsume(this.gameZork, CONSUME_PATTERN);
        store.setNextChain(consume);

        return lookAround;
    }

    @Override
    protected String getWelcoming() {
        return this.gameZork.welcomeMessage();
    }
}
