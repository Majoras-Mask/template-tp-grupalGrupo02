package ar.fiuba.tdd.tp;

import motor.Game;
import motor.GameCreator;
import motor.GameCreatorConcrete;
import motor.GameFetchQuest;
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
