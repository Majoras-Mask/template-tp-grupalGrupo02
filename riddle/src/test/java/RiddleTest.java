import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class RiddleTest {
    private final RiddleBuilder builder = new RiddleBuilder();
    private Game game;
    private static final int PLAYERID = 1;
    private static final String WON_MESSAGE = "Won";

    @Before
    public void init() {
        game = builder.build();
        game.joinPlayer(PLAYERID);
    }

    @Test
    public void winGame() {
        game.command(PLAYERID, "take sheep");
        game.command(PLAYERID, "cross northShore");
        game.command(PLAYERID, "leave sheep");
        game.command(PLAYERID, "cross southShore");
        game.command(PLAYERID, "take wolf");
        game.command(PLAYERID, "cross northShore");
        game.command(PLAYERID, "leave wolf");
        game.command(PLAYERID, "take sheep");
        game.command(PLAYERID, "cross southShore");
        game.command(PLAYERID, "leave sheep");
        game.command(PLAYERID, "take cabbage");
        game.command(PLAYERID, "cross northShore");
        game.command(PLAYERID, "leave cabbage");
        game.command(PLAYERID, "cross southShore");
        game.command(PLAYERID, "take sheep");
        game.command(PLAYERID, "cross northShore");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "leave sheep"));
    }

    @Test
    public void takeWolf() {
        assertEquals("take wolf on boat",game.command(PLAYERID, "take wolf"));
    }

    @Test
    public void takeSheepAfterTakedWolf() {
        game.command(PLAYERID, "take wolf");
        assertEquals("Can't do take on sheep",game.command(PLAYERID, "take sheep"));
    }

    @Test
    public void takeCabbageAfterTakedWolf() {
        game.command(PLAYERID, "take wolf");
        assertEquals("Can't do take on cabbage",game.command(PLAYERID, "take cabbage"));
    }

    @Test
    public void takeSheep() {
        assertEquals("take sheep on boat",game.command(PLAYERID, "take sheep"));
    }

    @Test
    public void takeWolfAfterTakedSheep() {
        game.command(PLAYERID, "take sheep");
        assertEquals("Can't do take on wolf",game.command(PLAYERID, "take wolf"));
    }

    @Test
    public void takeCabbageAfterTakedSheep() {
        game.command(PLAYERID, "take sheep");
        assertEquals("Can't do take on cabbage",game.command(PLAYERID, "take cabbage"));
    }

    @Test
    public void takeCabbage() {
        assertEquals("take cabbage on boat",game.command(PLAYERID, "take cabbage"));
    }

    @Test
    public void takeWolfAfterTakedCabbage() {
        game.command(PLAYERID, "take cabbage");
        assertEquals("Can't do take on wolf",game.command(PLAYERID, "take wolf"));
    }

    @Test
    public void takeSheepAfterTakedCabbage() {
        game.command(PLAYERID, "take cabbage");
        assertEquals("Can't do take on sheep",game.command(PLAYERID, "take sheep"));
    }

    @Test
    public void cantCrossWhenTheyEatEachOthers() {
        assertEquals("Can't do cross on northShore",game.command(PLAYERID, "cross northShore"));
    }

    @Test
    public void cantCrossIfTheWolfEatsTheSheep() {
        game.command(PLAYERID, "take cabbage");
        assertEquals("Can't do cross on southShore",game.command(PLAYERID, "cross southShore"));
    }

    @Test
    public void cantCrossIfTheSheepEatsTheCabbage() {
        game.command(PLAYERID, "take wolf");
        assertEquals("Can't do cross on southShore",game.command(PLAYERID, "cross southShore"));
    }

    @Test
    public void cantLeaveSheepIfYouDontHaveOne() {
        assertEquals("Can't do leave on sheep",game.command(PLAYERID, "leave sheep"));
    }

    @Test
    public void cantLeaveWolfIfYouDontHaveOne() {
        assertEquals("Can't do leave on wolf",game.command(PLAYERID, "leave wolf"));
    }

    @Test
    public void cantLeaveCabbageIfYouDontHaveOne() {
        assertEquals("Can't do leave on cabbage",game.command(PLAYERID, "leave cabbage"));
    }

    @Test
    public void canTakeCabbageAfterLeaveSomething() {
        game.command(PLAYERID, "take sheep");
        game.command(PLAYERID, "leave sheep");
        assertEquals("take cabbage on boat",game.command(PLAYERID, "take cabbage"));
    }

    @Test
    public void canTakeSheepAfterLeaveSomething() {
        game.command(PLAYERID, "take wolf");
        game.command(PLAYERID, "leave wolf");
        assertEquals("take sheep on boat",game.command(PLAYERID, "take sheep"));
    }

    @Test
    public void canTakeWolfAfterLeaveSomething() {
        game.command(PLAYERID, "take cabbage");
        game.command(PLAYERID, "leave cabbage");
        assertEquals("take wolf on boat",game.command(PLAYERID, "take wolf"));
    }

}
