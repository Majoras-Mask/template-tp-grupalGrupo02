package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HanoiTest {
    private final HanoiBuilder builder = new HanoiBuilder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        game.command("take disk1 from stack1");
        game.command("put disk1 on stack3");
        game.command("take disk2 from stack1");
        game.command("put disk2 on stack2");
        game.command("take disk1 from stack3");
        game.command("put disk1 on stack2");
        game.command("take disk3 from stack1");
        game.command("put disk3 on stack3");
        game.command("take disk1 from stack2");
        game.command("put disk1 on stack1");
        game.command("take disk2 from stack2");
        game.command("put disk2 on stack3");
        game.command("take disk1 from stack1");
        assertEquals("You won the game!", game.command("put disk1 on stack3"));
    }

}
