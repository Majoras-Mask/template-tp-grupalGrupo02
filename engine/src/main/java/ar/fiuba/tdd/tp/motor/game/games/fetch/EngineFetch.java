package ar.fiuba.tdd.tp.motor.game.games.fetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;

public class EngineFetch extends Engine {
    public EngineFetch() {
        this.game = new GameFetch();
    }

    @Override
    public ChainCommandCreator getChainOfCommands(Game game) {
        //ChainCommandCreator lookAround = new ChainFetchLookAround((GameFetch)this.games);
        //ChainCommandCreator pickStick = new ChainFetchPickStick((GameFetch)this.games);

        //lookAround.setNextChain(pickStick);

        //return lookAround;
        return null;
    }
}
