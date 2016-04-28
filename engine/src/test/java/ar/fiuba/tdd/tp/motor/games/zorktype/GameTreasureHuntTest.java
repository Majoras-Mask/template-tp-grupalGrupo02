package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoortwo.GameOpenDoorTwo;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.treasurehunt.GameTreasureHunt;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by kevin on 28/04/16.
 */
public class GameTreasureHuntTest extends ZorkTypeGameTest {
    @Test
    public void losingGameTest() {
        setup(new GameTreasureHunt());
        this.engineZork.request("open box0");
        this.engineZork.request("pick key0");
        this.engineZork.request("open door0");
        this.engineZork.request("open closet0");
        this.engineZork.request("pick key1");
        this.engineZork.request("open door1");
        assertTrue(this.game.checkIfGameIsFinished());
        assertTrue(this.game.getGamePlayer().isDead());
    }

}
