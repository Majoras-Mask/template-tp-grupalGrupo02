import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("CPD-START")
public class OpenDoorMultiplayerTest {
    private static String PATH_TO_OPEN_DOOR = "../openDoorMultiplayer.jar";

    @Test
    public void testWinGame() throws GameLoadFailedException, PlayerJoinFailedException, UnknownPlayerException {
        GameDriver gameDriver = new ConcreteGameDriver();
        gameDriver.initGame(PATH_TO_OPEN_DOOR);

        String player1 = gameDriver.joinPlayer();
        String player2 = gameDriver.joinPlayer();


        gameDriver.sendCommand(player1,"look around");
        gameDriver.sendCommand(player1, "pick key");
        gameDriver.sendCommand(player1,"look around");

        gameDriver.sendCommand(player2, "pick key");
        gameDriver.sendCommand(player2, "open door");

        assertTrue(gameDriver.getCurrentState() == GameState.Running);

        gameDriver.sendCommand(player1, "open door");

        assertTrue(gameDriver.getCurrentState() == GameState.Win);
    }

}

