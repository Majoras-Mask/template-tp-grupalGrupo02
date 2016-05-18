package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoor1Test {
    private final OpenDoor1Builder builder = new OpenDoor1Builder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        game.command("pick key");
        assertEquals("You won the game!", game.command("open door"));
    }

    @Test
    public void alreadyWonGameOpenDoor() {
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("open door"));
    }

    @Test
    public void alreadyWonGamePickKey() {
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("pick key"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("something"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't do pick on something", game.command("pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command("something"));
    }

    @Test
    public void cantOpenDoorWithoutKey() {
        assertEquals("Can't do open on door", game.command("open door"));
    }

    @Test
    public void lookAround() {
        assertEquals("room1 has door-key-player", game.command("look around"));
    }
}
