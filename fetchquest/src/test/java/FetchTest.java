import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FetchTest {
    private final FetchBuilder builder = new FetchBuilder();
    private Game game;
    private static final int PLAYERID = 1;
    private static final String WON_MESSAGE = "Won";

    @Before
    public void init() {
        game = builder.build();
        game.joinPlayer(PLAYERID);
    }

    @Test
    public void winGame() {
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "pick stick"));
    }

    @Test
    public void alreadyWonGamePickStick() {
        game.command(PLAYERID, "pick stick");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "pick stick"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        game.command(PLAYERID, "pick stick");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        game.command(PLAYERID, "pick stick");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "something"));
    }

    @Test
    public void lookAround() {
        assertEquals("room has stick-player", game.command(PLAYERID, "look around"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't do pick on something", game.command(PLAYERID, "pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command(PLAYERID, "something"));
    }
}