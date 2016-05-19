package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RiddleTest {
    private final RiddleBuilder builder = new RiddleBuilder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        game.command("take sheep");
        game.command("cross northShore");
        game.command("leave sheep");
        game.command("cross southShore");
        game.command("take wolf");
        game.command("cross northShore");
        game.command("leave wolf");
        game.command("take sheep");
        game.command("cross southShore");
        game.command("leave sheep");
        game.command("take cabbage");
        game.command("cross northShore");
        game.command("leave cabbage");
        game.command("cross southShore");
        game.command("take sheep");
        game.command("cross northShore");
        assertEquals("You won the game!", game.command("leave sheep"));
    }

    @Test
    public void takeWolf() {
        assertEquals("take wolf on boat",game.command("take wolf"));
    }

    @Test
    public void takeSheepAfterTakedWolf() {
        game.command("take wolf");
        assertEquals("Can't do take on sheep",game.command("take sheep"));
    }

    @Test
    public void takeCabbageAfterTakedWolf() {
        game.command("take wolf");
        assertEquals("Can't do take on cabbage",game.command("take cabbage"));
    }

    @Test
    public void takeSheep() {
        assertEquals("take sheep on boat",game.command("take sheep"));
    }

    @Test
    public void takeWolfAfterTakedSheep() {
        game.command("take sheep");
        assertEquals("Can't do take on wolf",game.command("take wolf"));
    }

    @Test
    public void takeCabbageAfterTakedSheep() {
        game.command("take sheep");
        assertEquals("Can't do take on cabbage",game.command("take cabbage"));
    }

    @Test
    public void takeCabbage() {
        assertEquals("take cabbage on boat",game.command("take cabbage"));
    }

    @Test
    public void takeWolfAfterTakedCabbage() {
        game.command("take cabbage");
        assertEquals("Can't do take on wolf",game.command("take wolf"));
    }

    @Test
    public void takeSheepAfterTakedCabbage() {
        game.command("take cabbage");
        assertEquals("Can't do take on sheep",game.command("take sheep"));
    }

    @Test
    public void cantCrossWhenTheyEatEachOthers() {
        assertEquals("Can't do cross on northShore",game.command("cross northShore"));
    }

    @Test
    public void cantCrossIfTheWolfEatsTheSheep() {
        game.command("take cabbage");
        assertEquals("Can't do cross on southShore",game.command("cross southShore"));
    }

    @Test
    public void cantCrossIfTheSheepEatsTheCabbage() {
        game.command("take wolf");
        assertEquals("Can't do cross on southShore",game.command("cross southShore"));
    }

    @Test
    public void cantLeaveSheepIfYouDontHaveOne() {
        assertEquals("Can't do leave on sheep",game.command("leave sheep"));
    }

    @Test
    public void cantLeaveWolfIfYouDontHaveOne() {
        assertEquals("Can't do leave on wolf",game.command("leave wolf"));
    }

    @Test
    public void cantLeaveCabbageIfYouDontHaveOne() {
        assertEquals("Can't do leave on cabbage",game.command("leave cabbage"));
    }

    @Test
    public void canTakeCabbageAfterLeaveSomething() {
        game.command("take sheep");
        game.command("leave sheep");
        assertEquals("take cabbage on boat",game.command("take cabbage"));
    }

    @Test
    public void canTakeSheepAfterLeaveSomething() {
        game.command("take wolf");
        game.command("leave wolf");
        assertEquals("take sheep on boat",game.command("take sheep"));
    }

    @Test
    public void canTakeWolfAfterLeaveSomething() {
        game.command("take cabbage");
        game.command("leave cabbage");
        assertEquals("take wolf on boat",game.command("take wolf"));
    }

}
