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

}
