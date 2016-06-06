package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionAndOrTest {
    @Test
    public void checkAndTrueFalse() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition alwaysTrue = new ConditionAlwaysTrue();
        Condition condition = alwaysTrue.and(alwaysTrue.not());
        assertFalse(condition.check(gameConcrete));
    }

    @Test
    public void checkAndTrueTrue() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition alwaysTrue = new ConditionAlwaysTrue();
        Condition condition = alwaysTrue.and(alwaysTrue);
        assertTrue(condition.check(gameConcrete));
    }

    @Test
    public void checkOrTrueFalse() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition alwaysTrue = new ConditionAlwaysTrue();
        Condition condition = alwaysTrue.or(alwaysTrue.not());
        assertTrue(condition.check(gameConcrete));
    }

    @Test
    public void checkOrFalseFalse() throws Exception {
        GameConcrete gameConcrete = new GameConcrete();
        Condition alwaysTrue = new ConditionAlwaysTrue();
        Condition condition = alwaysTrue.not().and(alwaysTrue.not());
        assertFalse(condition.check(gameConcrete));
    }

}