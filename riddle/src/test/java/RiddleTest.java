import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("CPD-START")
public class RiddleTest {

    private static String PATH_TO_RIDDLE = "../riddle.jar";

    @Test
    public void testWinGame() throws GameLoadFailedException, PlayerJoinFailedException, UnknownPlayerException {
        GameDriver gameDriver = new ConcreteGameDriver();
        gameDriver.initGame(PATH_TO_RIDDLE);

        String player = gameDriver.joinPlayer();

        gameDriver.sendCommand(player, "take sheep");
        gameDriver.sendCommand(player, "cross north-shore");
        gameDriver.sendCommand(player, "leave sheep");
        gameDriver.sendCommand(player, "cross south-shore");
        gameDriver.sendCommand(player, "take wolf");
        gameDriver.sendCommand(player, "cross north-shore");
        gameDriver.sendCommand(player, "leave wolf");
        gameDriver.sendCommand(player, "take sheep");
        gameDriver.sendCommand(player, "cross south-shore");
        gameDriver.sendCommand(player, "leave sheep");
        gameDriver.sendCommand(player, "take cabbage");
        gameDriver.sendCommand(player, "cross north-shore");
        gameDriver.sendCommand(player, "leave cabbage");
        gameDriver.sendCommand(player, "cross south-shore");
        gameDriver.sendCommand(player, "take sheep");

        assertTrue(gameDriver.getCurrentState() == GameState.Running);

        gameDriver.sendCommand(player, "cross north-shore");
        gameDriver.sendCommand(player, "leave sheep");

        assertTrue(gameDriver.getCurrentState() == GameState.Win);
    }
}
