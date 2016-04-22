package ar.fiuba.tdd.tp.motor.games.fetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.games.fetch.chainfetch.ChainFetchLookAround;
import ar.fiuba.tdd.tp.motor.games.fetch.chainfetch.ChainFetchPickStick;

public class EngineFetch extends Engine {
    public EngineFetch() {
        this.game = new GameFetch();
    }

    @Override
    public ChainCommandCreator getChainOfCommands(Game game) {
        ChainCommandCreator lookAround = new ChainFetchLookAround((GameFetch)this.game);
        ChainCommandCreator pickStick = new ChainFetchPickStick((GameFetch)this.game);

        lookAround.setNextChain(pickStick);

        return lookAround;
    }
}
