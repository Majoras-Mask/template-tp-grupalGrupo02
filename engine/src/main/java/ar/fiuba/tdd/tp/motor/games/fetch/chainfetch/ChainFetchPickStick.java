package ar.fiuba.tdd.tp.motor.games.fetch.chainfetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.games.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.games.fetch.commandfetch.FetchCommandPickStick;

public class ChainFetchPickStick extends ChainCommandCreator {
    private GameFetch game;

    public ChainFetchPickStick(GameFetch gameFetch) {
        this.game = gameFetch;
    }

    @Override
    public GameCommand createCommand(String message) {
        if ( message.toLowerCase().startsWith("pick stick") ) {
            return new FetchCommandPickStick(this.game);
        } else {
            return super.createCommand(message);
        }
    }
}
