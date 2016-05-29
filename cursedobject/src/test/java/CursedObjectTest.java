import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class CursedObjectTest {
    private final CursedObjectBuilder builder = new CursedObjectBuilder();
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
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        game.command(PLAYERID, "say hello thief");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "open door"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't do pick on something", game.command(PLAYERID, "pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command(PLAYERID, "something"));
    }

    @Test
    public void cantOpenDoorWithoutKey() {
        assertEquals("Can't do open on door", game.command(PLAYERID, "open door"));
    }

    //Tests in first room
    @Test
    public void lookAroundFirstRoom() {
        assertEquals("room1 has door-player1-cursedObject", game.command(PLAYERID, "look around"));
    }

    @Test
    public void lookAroundFirstRoomAfterPickedCursedObject() {
        game.command(PLAYERID, "pick cursedObject");
        assertEquals("room1 has door-player1", game.command(PLAYERID, "look around"));
    }

    @Test
    public void pickCursedObject() {
        assertEquals("You picked a cursedObject",game.command(PLAYERID, "pick cursedObject"));
    }

    @Test
    public void pickdoor() {
        assertEquals("Can't do pick on door",game.command(PLAYERID, "pick door"));
    }

    @Test
    public void openCursedObject() {
        assertEquals("Can't do open on cursedObject", game.command(PLAYERID, "open cursedObject"));
    }

    @Test
    public void openDoor() {
        assertEquals("Can't do open on door", game.command(PLAYERID, "open door"));
    }

    @Test
    public void openDoorAfterPickedCursedObject() {
        game.command(PLAYERID, "pick cursedObject");
        assertEquals("You opened a door and walked to room2",game.command(PLAYERID, "open door"));
    }

    @Test
    public void sayHelloDoor() {
        assertEquals("Can't do hello on door",  game.command(PLAYERID, "say hello door"));
    }

    @Test
    public void sayHelloCursedObject() {
        assertEquals("Can't do hello on cursedObject",  game.command(PLAYERID, "say hello cursedObject"));
    }
    //Tests in second room
    @Test
    public void lookAroundSecondRoom() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("room2 has door-player1-thief", game.command(PLAYERID, "look around"));
    }

    @Test
    public void lookAroundSecondRoomAfterTalkWithThief() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        game.command(PLAYERID, "say hello thief");
        assertEquals("room2 has door-player1", game.command(PLAYERID, "look around"));
    }

    @Test
    public void pickThief() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("Can't do pick on thief",  game.command(PLAYERID, "pick thief"));
    }

    @Test
    public void pickDoor() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("Can't do pick on door",  game.command(PLAYERID, "pick door"));
    }

    @Test
    public void pickDoorAfterWithoutCursedObject() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        game.command(PLAYERID, "say hello thief");
        assertEquals("Can't do pick on door",  game.command(PLAYERID, "pick door"));
    }

    @Test
    public void openThief() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("Can't do open on thief",  game.command(PLAYERID, "open thief"));
    }

    @Test
    public void openDoor2WithCursedObject() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("Can't do open on door",  game.command(PLAYERID, "open door"));
    }

    @Test
    public void sayHelloThief() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("The thief stoled your cursedObject and ran away",  game.command(PLAYERID, "say hello thief"));
    }

    @Test
    public void sayHelloDoor2() {
        game.command(PLAYERID, "pick cursedObject");
        game.command(PLAYERID, "open door");
        assertEquals("Can't do hello on door",  game.command(PLAYERID, "say hello door"));
    }
}