package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ConditionSizeTest {
    @Test
    public void check() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        ObjectConcrete item = new ObjectConcrete("item");
        game.addObject(object);
        game.addObject(item);

        Condition conditionSizeEqualZero = new ConditionSize(new ValueConstant("objeto"), 0, new ConditionSizeOperationEqual());
        Condition conditionSizeLessThanTwo = new ConditionSize(new ValueConstant("objeto"), 2, new ConditionSizeOperationLessThan());

        assertTrue(conditionSizeEqualZero.check(game));
        assertTrue(conditionSizeLessThanTwo.check(game));

        object.add(item);
        assertFalse(conditionSizeEqualZero.check(game));
        assertTrue(conditionSizeLessThanTwo.check(game));

    }

}