import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings("CPD-START")
public class HanoiTest {
    private final HanoiBuilder builder = new HanoiBuilder();
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
        game.command(PLAYERID, "take disk1 from stack1");
        game.command(PLAYERID, "put disk1 on stack3");
        game.command(PLAYERID, "take disk2 from stack1");
        game.command(PLAYERID, "put disk2 on stack2");
        game.command(PLAYERID, "take disk1 from stack3");
        game.command(PLAYERID, "put disk1 on stack2");
        game.command(PLAYERID, "take disk3 from stack1");
        game.command(PLAYERID, "put disk3 on stack3");
        game.command(PLAYERID, "take disk1 from stack2");
        game.command(PLAYERID, "put disk1 on stack1");
        game.command(PLAYERID, "take disk2 from stack2");
        game.command(PLAYERID, "put disk2 on stack3");
        game.command(PLAYERID, "take disk1 from stack1");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "put disk1 on stack3"));
    }

    @Test
    public void takeDisk1EmptyStack2() {
        assertEquals("Can't do take on disk1 from stack2",game.command(PLAYERID, "take disk1 from stack2"));
    }

    @Test
    public void takeDisk1EmptyStack3() {
        assertEquals("Can't do take on disk1 from stack3",game.command(PLAYERID, "take disk1 from stack3"));
    }

    @Test
    public void takeDisk1Stack1() {
        assertEquals("Picked disk1 from stack1",game.command(PLAYERID, "take disk1 from stack1"));
    }

    @Test
    public void takeDisk2Stack1() {
        assertEquals("Can't do take on disk2",game.command(PLAYERID, "take disk2 from stack1"));
    }

    @Test
    public void takeDisk3Stack1() {
        assertEquals("Can't do take on disk3",game.command(PLAYERID, "take disk3 from stack1"));
    }

    @Test
    public void moveDisk1ToStack2() {
        game.command(PLAYERID, "take disk1 from stack1");
        assertEquals("Placed disk1 on stack2",game.command(PLAYERID, "put disk1 on stack2"));
    }

    @Test
    public void cantMoveDisk2OverDisk1() {
        game.command(PLAYERID, "take disk1 from stack1");
        game.command(PLAYERID, "put disk1 on stack2");
        game.command(PLAYERID, "take disk2 from stack1");
        assertEquals("Can't do put on disk2",game.command(PLAYERID, "put disk2 on stack2"));
    }

    @Test
    public void cantMoveDisk1ToUndefinedStack() {
        game.command(PLAYERID, "take disk1 from stack1");
        assertEquals("Can't do put with disk1 on stack4",game.command(PLAYERID, "put disk1 on stack4"));
    }

    @Test
    public void cantTakeStak() {
        assertEquals("Can't do take on stack1 from stack1", game.command(PLAYERID, "take stack1 from stack1"));
    }

    @Test
    public void cantWinIfMoveAllStack1toStack2() {
        game.command(PLAYERID, "take disk1 from stack1");
        game.command(PLAYERID, "put disk1 on stack2");
        game.command(PLAYERID, "take disk2 from stack1");
        game.command(PLAYERID, "put disk2 on stack3");
        game.command(PLAYERID, "take disk1 from stack2");
        game.command(PLAYERID, "put disk1 on stack3");
        game.command(PLAYERID, "take disk3 from stack1");
        game.command(PLAYERID, "put disk3 on stack2");
        game.command(PLAYERID, "take disk1 from stack3");
        game.command(PLAYERID, "put disk1 on stack1");
        game.command(PLAYERID, "take disk2 from stack3");
        game.command(PLAYERID, "put disk2 on stack2");
        game.command(PLAYERID, "take disk1 from stack1");
        assertNotEquals(WON_MESSAGE,game.command(PLAYERID, "put disk1 on stack2"));
    }
}