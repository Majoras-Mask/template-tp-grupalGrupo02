package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionCheckGameStateTest {
    @Test
    public void check() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        ConditionCheckGameState condition = new ConditionCheckGameState(gameConcrete, GameState.Running);
        assertTrue(condition.check(gameConcrete));
    }

    @Test
    public void checkNegative() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        ConditionCheckGameState condition = new ConditionCheckGameState(gameConcrete, GameState.Lost);
        assertFalse(condition.check(gameConcrete));
    }

}