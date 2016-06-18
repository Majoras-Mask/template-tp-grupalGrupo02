import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("CPD-START")
public class HanoiTest {
    private static String PATH_TO_HANOI = "../hanoi.jar";

    @Test
    public void testWinGame() throws GameLoadFailedException, PlayerJoinFailedException, UnknownPlayerException {
        GameDriver gameDriver = new ConcreteGameDriver();
        gameDriver.initGame(PATH_TO_HANOI);

        String player = gameDriver.joinPlayer();

        gameDriver.sendCommand(player, "move top stack1 stack3");
        gameDriver.sendCommand(player, "move top stack1 stack2");
        gameDriver.sendCommand(player, "move top stack3 stack2");
        gameDriver.sendCommand(player, "move top stack1 stack3");
        gameDriver.sendCommand(player, "move top stack2 stack1");
        gameDriver.sendCommand(player, "move top stack2 stack3");

        assertTrue(gameDriver.getCurrentState() == GameState.Running);

        gameDriver.sendCommand(player, "move top stack1 stack3");

        assertTrue(gameDriver.getCurrentState() == GameState.Win);
    }

}
