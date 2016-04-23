package ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.Engine;
import ar.fiuba.tdd.tp.motor.Game;

public class EngineFetch extends Engine {
    public EngineFetch() {
        this.game = new GameFetch();
    }

    @Override
    public ChainCommandCreator getChainOfCommands(Game game) {
        return null;
    }
}
