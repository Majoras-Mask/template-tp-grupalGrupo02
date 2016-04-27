package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;

public class ZorkTypeGameTest {

    protected ZorkTypeGame game;
    protected EngineZork engineZork;

    protected void setup(ZorkTypeGame game) {
        this.game = game;
        this.engineZork = new EngineZork(this.game);
    }

    protected String getResponseFromAction(String action) {
        return this.engineZork.request(action);
    }
}
