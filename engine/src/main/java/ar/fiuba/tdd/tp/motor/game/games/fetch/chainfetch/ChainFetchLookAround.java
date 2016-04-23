package ar.fiuba.tdd.tp.motor.game.games.fetch.chainfetch;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.game.games.fetch.commandfetch.FetchCommandLookAround;

public class ChainFetchLookAround extends ChainFetchCommonParse {
    public ChainFetchLookAround(GameFetch gameFetch, String patternString) {
        super(gameFetch, patternString);
    }

    public GameCommand factoryCommand(String message) {
        return new FetchCommandLookAround();
    }
}
