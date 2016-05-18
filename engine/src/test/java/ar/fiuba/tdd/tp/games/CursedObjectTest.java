package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CursedObjectTest {
    private final CursedObjectBuilder builder = new CursedObjectBuilder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        game.command("pick cursed object");
        game.command("open door");
        game.command("say hello thief");
        assertEquals("You won the game!", game.command("open door"));
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
    public void lookAroundFirstRoom() {
        assertEquals("room1 has door-cursed object-player", game.command("look around"));
    }

    @Test
    public void lookAroundSecondRoom() {
        game.command("pick cursed object");
        game.command("open door");
        assertEquals("room2 has door-thief-player", game.command("look around"));
    }

}
