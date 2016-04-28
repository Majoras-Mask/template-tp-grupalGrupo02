package ar.fiuba.tdd.tp.motor.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.cursedobject.GameCursedObject;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GameCursedObjectTest extends ZorkTypeGameTest {
    @Test
    public void winningGameTest() {
        setup(new GameCursedObject());
        getResponseFromAction("pick key0");
        getResponseFromAction("open door0");
        getResponseFromAction("talk thief0");
        getResponseFromAction("look around");
        getResponseFromAction("open door0");
        assertTrue(this.game.checkIfGameIsFinished());
    }
}
