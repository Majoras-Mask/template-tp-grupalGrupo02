package ar.fiuba.tdd.tp.motor.games.zorktype.fetch;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class GameFetchTest {

    @Test
    public void onGameCreationNoWinning() {
        GameFetch game = new GameFetch();

        assertFalse(game.checkIfGameIsFinished());
    }

    @Test
    public void winningTheGame() {
        GameFetch game = new GameFetch();
        EngineZork engineZork = new EngineZork(game);

        assertEquals(engineZork.request("pick stick0"), "You picked stick0.");
    }
}
