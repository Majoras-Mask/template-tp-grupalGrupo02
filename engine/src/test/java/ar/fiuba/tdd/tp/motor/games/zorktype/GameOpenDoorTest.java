package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoor.GameOpenDoor;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GameOpenDoorTest extends ZorkTypeGameTest {

    @Test
    public void cantGoThroughLockedDoor() {
        setup(new GameOpenDoor());
        assertEquals(getResponseFromAction("open door0"),"Can't open door0.");
    }

    @Test
    public void winningGameTest() {
        setup(new GameOpenDoor());
        this.engineZork.request("pick key0");
        this.engineZork.request("open door0");
        assertTrue(this.game.checkIfGameIsFinished());
    }
}
