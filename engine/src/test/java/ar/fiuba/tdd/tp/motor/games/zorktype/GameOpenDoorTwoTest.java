package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoortwo.GameOpenDoorTwo;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GameOpenDoorTwoTest extends ZorkTypeGameTest {


    @Test
    public void winningGameTest() {
        setup(new GameOpenDoorTwo());
        this.engineZork.request("open box0");
        this.engineZork.request("pick key0");
        this.engineZork.request("open door0");
        assertTrue(this.game.checkIfGameIsFinished());
    }

}
