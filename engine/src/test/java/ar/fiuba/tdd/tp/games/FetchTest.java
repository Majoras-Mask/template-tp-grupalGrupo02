package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FetchTest {
    private final FetchBuilder builder = new FetchBuilder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        assertEquals("You won the game!", game.command("pick stick"));
    }

    @Test
    public void alreadyWonGamePickStick() {
        game.command("pick stick");
        assertEquals("You won the game!", game.command("pick stick"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        game.command("pick stick");
        assertEquals("You won the game!", game.command("look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        game.command("pick stick");
        assertEquals("You won the game!", game.command("something"));
    }

    @Test
    public void lookAround() {
        assertEquals("room has stick-player", game.command("look around"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't pick something", game.command("pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command("something"));
    }
}
