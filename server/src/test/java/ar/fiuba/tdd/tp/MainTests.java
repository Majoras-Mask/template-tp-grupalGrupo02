package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.motor.games.Game;
import ar.fiuba.tdd.tp.motor.games.getter.GameGetter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void gameGetterTest() {
        GameGetter gameGetter = GameGetter.getChainOfGameGetters();
        Game game = gameGetter.getGame("GameFetchQuest");
        assertEquals(game.getClass().getSimpleName(), "GameFetchQuest");
        game = gameGetter.getGame("Garbage name");
        assertEquals(game.getClass().getSimpleName(), "GameDefault");
    }
}
