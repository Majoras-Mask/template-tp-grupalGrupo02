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
        game.command("pick cursedObject");
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

    //Tests in first room
    @Test
    public void lookAroundFirstRoom() {
        assertEquals("room1 has door-cursedObject-player", game.command("look around"));
    }

    @Test
    public void lookAroundFirstRoomAfterPickedCursedObject() {
        game.command("pick cursedObject");
        assertEquals("room1 has door-player", game.command("look around"));
    }

    @Test
    public void pickCursedObject() {
        assertEquals("You picked a cursedObject",game.command("pick cursedObject"));
    }

    @Test
    public void pickdoor() {
        assertEquals("Can't do pick on door",game.command("pick door"));
    }

    @Test
    public void openCursedObject() {
        assertEquals("Can't do open on cursedObject", game.command("open cursedObject"));
    }

    @Test
    public void openDoor() {
        assertEquals("Can't do open on door", game.command("open door"));
    }

    @Test
    public void openDoorAfterPickedCursedObject() {
        game.command("pick cursedObject");
        assertEquals("You opened a door and walked to room2",game.command("open door"));
    }

    @Test
    public void sayHelloDoor() {
        assertEquals("Can't do hello on door",  game.command("say hello door"));
    }

    @Test
    public void sayHelloCursedObject() {
        assertEquals("Can't do hello on cursedObject",  game.command("say hello cursedObject"));
    }
    //Tests in second room
    @Test
    public void lookAroundSecondRoom() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("room2 has door-thief-player", game.command("look around"));
    }

    @Test
    public void lookAroundSecondRoomAfterTalkWithThief() {
        game.command("pick cursedObject");
        game.command("open door");
        game.command("say hello thief");
        assertEquals("room2 has door-player", game.command("look around"));
    }

    @Test
    public void pickThief() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("Can't do pick on thief",  game.command("pick thief"));
    }

    @Test
    public void pickDoor() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("Can't do pick on door",  game.command("pick door"));
    }

    @Test
    public void pickDoorAfterWithoutCursedObject() {
        game.command("pick cursedObject");
        game.command("open door");
        game.command("say hello thief");
        assertEquals("Can't do pick on door",  game.command("pick door"));
    }

    @Test
    public void openThief() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("Can't do open on thief",  game.command("open thief"));
    }

    @Test
    public void openDoor2WithCursedObject() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("Can't do open on door",  game.command("open door"));
    }

    @Test
    public void sayHelloThief() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("The thief stoled your cursedObject and ran away",  game.command("say hello thief"));
    }

    @Test
    public void sayHelloDoor2() {
        game.command("pick cursedObject");
        game.command("open door");
        assertEquals("Can't do hello on door",  game.command("say hello door"));
    }

}
