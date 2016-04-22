package ar.fiuba.tdd.tp.motor.games.fetch.chainfetch;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.games.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.games.fetch.commandfetch.FetchCommandPickStick;

public class ChainFetchPickStick extends ChainFetchCommonParse {
    public ChainFetchPickStick(GameFetch gameFetch, String patternString) {
        super(gameFetch, patternString);
    }

    public GameCommand factoryCommand(String message) {
        return new FetchCommandPickStick(this.gameFetch);
    }
}
