package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.motor.games.Game;
import ar.fiuba.tdd.tp.motor.gettergames.GameGetter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void gameGetterTest() {
        GameGetter gameGetter = GameGetter.getChainOfGameGetters();
        Game game = gameGetter.getGame("Fetch Quest");
        assertEquals(game.getClass().getSimpleName(), "GameFetchQuest");
        game = gameGetter.getGame("Garbage name");
        assertEquals(game.getClass().getSimpleName(), "GameDefault");
    }
}
