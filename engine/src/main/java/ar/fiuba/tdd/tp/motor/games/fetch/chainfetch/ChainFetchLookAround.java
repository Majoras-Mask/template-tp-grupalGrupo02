package ar.fiuba.tdd.tp.motor.games.fetch.chainfetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.games.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.games.fetch.commandfetch.FetchCommandLookAround;

public class ChainFetchLookAround extends ChainCommandCreator {
    private GameFetch gameFetch;

    public ChainFetchLookAround(GameFetch gameFetch) {
        this.gameFetch = gameFetch;
    }

    @Override
    public GameCommand createCommand(String message) {
        /*if ( message.toLowerCase().startsWith("look around") ) {
            return new FetchCommandLookAround(this.gameFetch);
        } else {
            return super.createCommand(message);
        }*/
        return new FetchCommandLookAround(this.gameFetch);
    }
}
