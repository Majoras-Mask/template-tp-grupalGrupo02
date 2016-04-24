package ar.fiuba.tdd.tp.motor.games.zorktype.fetch;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class GameFetchTest {

    private GameFetch game = new GameFetch();
    private EngineZork engineZork = new EngineZork(this.game);

    private String getResponseFromAction(String action) {
        return this.engineZork.request(action);
    }

    @Test
    public void onGameCreationNoWinningTest() {
        assertFalse(game.checkIfGameIsFinished());
    }

    @Test
    public void lookAroundSeeItemsTest() {
        assertEquals(getResponseFromAction("look around"), "room0 has: A stick0.");
    }

    @Test
    public void winningGame() {
        getResponseFromAction("pick stick0");
        assertTrue(this.game.checkIfGameIsFinished());
    }
}
