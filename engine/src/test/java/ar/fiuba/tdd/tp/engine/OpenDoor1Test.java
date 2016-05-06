package ar.fiuba.tdd.tp.engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by manuelcruz on 05/05/2016.
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
    public void lookAroundBeforePickKey() {
        assertEquals("In room1 there is a key", openDoor1.command("look around"));
    }

    @Test
    public void lookAroundAfterPickKey() {
        openDoor1.command("pick key");
        assertEquals("In room1 there is", openDoor1.command("look around"));
    }

    @Test
    public void pickKey() {
        assertEquals("Picked key", openDoor1.command("pick key"));
    }

    @Test
    public void pickKeyTwice() {
        openDoor1.command("pick key");
        assertEquals("Can't pick key", openDoor1.command("pick key"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", openDoor1.command("something"));
    }
}
