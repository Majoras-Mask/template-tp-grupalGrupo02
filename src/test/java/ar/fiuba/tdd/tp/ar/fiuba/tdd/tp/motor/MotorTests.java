package ar.fiuba.tdd.tp.ar.fiuba.tdd.tp.motor;

import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.GameCreator;
import ar.fiuba.tdd.tp.motor.GameCreatorConcrete;
import ar.fiuba.tdd.tp.motor.GameFetchQuest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotorTests {
    @Test
    public void dummy() {
        assertEquals(0, 0);
    }

    @Test
    public void testCreateGameFetchQuest() {
        Game fetchGame = new GameFetchQuest();
        GameCreator gameFactory = new GameCreatorConcrete();

        fetchGame = gameFactory.createGame(fetchGame);

        assertEquals(fetchGame.getClass().getSimpleName(), "GameFetchQuest");
    }
}
