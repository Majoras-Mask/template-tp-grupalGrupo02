package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionAndOrTest {
    @Test
    public void check() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition condition = new ConditionAndOr(true, new ConditionAlwaysTrue(), new ConditionNOT(new ConditionAlwaysTrue()));
        assertFalse(condition.check(gameConcrete));
    }

    @Test
    public void check2() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition condition = new ConditionAndOr(true, new ConditionAlwaysTrue(), new ConditionAlwaysTrue());
        assertTrue(condition.check(gameConcrete));
    }

}