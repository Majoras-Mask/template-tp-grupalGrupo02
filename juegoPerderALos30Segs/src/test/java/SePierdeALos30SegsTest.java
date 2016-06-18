import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("CPD-START")
public class SePierdeALos30SegsTest {
    private static String PATH_TO_SE_PIERDE = "../juegoPerderALos30Segs.jar";

    @Test
    public void testWinGame() throws GameLoadFailedException, PlayerJoinFailedException, UnknownPlayerException {
        GameDriver gameDriver = new ConcreteGameDriver();
        gameDriver.initGame(PATH_TO_SE_PIERDE);

        GameConcrete game = (GameConcrete) gameDriver.getGame();

        String player = gameDriver.joinPlayer();

        assertTrue(gameDriver.getCurrentState() == GameState.Running);

        game.update(30 * 1000 - 1); // Pasan 30 segundos menos 1 milisegundo.

        assertTrue(gameDriver.getCurrentState() == GameState.Running);

        game.update(1); // El milisegundo restante

        assertTrue(gameDriver.getCurrentState() == GameState.Lost);
    }

}

