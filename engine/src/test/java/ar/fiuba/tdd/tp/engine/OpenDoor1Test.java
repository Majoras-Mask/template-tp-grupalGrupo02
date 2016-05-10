package ar.fiuba.tdd.tp.engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public class OpenDoor1Test {
    private OpenDoor1 openDoor1;

    @Before
    public void init() {
        openDoor1 = new OpenDoor1();
    }

    @Test
    public void winGame() {
        openDoor1.command("pick key");
        assertEquals("You won the game!", openDoor1.command("open door"));
    }

    @Test
    public void openDoorWithoutKey() {
        assertEquals("no cumple la condicion", openDoor1.command("open door"));
    }

    @Test
    public void alreadyWonGamePickKey() {
        openDoor1.command("pick key");
        openDoor1.command("open door");
        assertEquals("You won the game!", openDoor1.command("pick key"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        openDoor1.command("pick key");
        openDoor1.command("open door");
        assertEquals("You won the game!", openDoor1.command("look around"));
    }

    @Test
    public void alreadyWonGameOpenDoor() {
        openDoor1.command("pick key");
        openDoor1.command("open door");
        assertEquals("You won the game!", openDoor1.command("open door"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        openDoor1.command("pick key");
        openDoor1.command("open door");
        assertEquals("You won the game!", openDoor1.command("something"));
    }

    @Test
    public void lookAroundRoom1() {
        assertEquals("room1 has key", openDoor1.command("look around"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", openDoor1.command("something"));
    }
}
