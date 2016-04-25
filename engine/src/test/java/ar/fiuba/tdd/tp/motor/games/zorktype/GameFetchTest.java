package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class GameFetchTest extends ZorkTypeGameTest {

    @Test
    public void onGameCreationNoWinningTest() {
        setup(new GameFetch());
        assertFalse(game.checkIfGameIsFinished());
    }

    @Test
    public void lookAroundSeeItemsTest() {
        setup(new GameFetch());
        assertEquals(getResponseFromAction("look around"), "room0 has: A stick0.");
    }

    @Test
    public void winningGame() {
        setup(new GameFetch());
        getResponseFromAction("pick stick0");
        assertTrue(this.game.checkIfGameIsFinished());
    }
}
