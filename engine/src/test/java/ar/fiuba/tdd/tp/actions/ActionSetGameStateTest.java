package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionSetGameStateTest {
    @Test
    public void execute() throws Exception {
        GameConcrete game = new GameConcrete();
        ActionSetGameState action = new ActionSetGameState(game, GameState.Win);
        action.execute(game);
        assertTrue(game.getGameState() == GameState.Win);
    }

}