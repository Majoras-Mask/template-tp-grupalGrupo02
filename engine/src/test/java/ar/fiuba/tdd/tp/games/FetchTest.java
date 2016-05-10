package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.Fetch;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public class FetchTest {
    private Fetch fetch;

    @Before
    public void init() {
        fetch = new Fetch();
    }

    @Test
    public void winGame() {
        assertEquals("You won the game!", fetch.command("pick stick"));
    }

    @Test
    public void alreadyWonGamePickStick() {
        fetch.command("pick stick");
        assertEquals("You won the game!", fetch.command("pick stick"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        fetch.command("pick stick");
        assertEquals("You won the game!", fetch.command("look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        fetch.command("pick stick");
        assertEquals("You won the game!", fetch.command("something"));
    }

    @Test
    public void lookAround() {
        assertEquals("room has stick", fetch.command("look around"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", fetch.command("something"));
    }
}
