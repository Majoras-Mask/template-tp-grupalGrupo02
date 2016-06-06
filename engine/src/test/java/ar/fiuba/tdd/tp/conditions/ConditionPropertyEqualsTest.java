package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ConditionPropertyEqualsTest {
    @Test
    public void check() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        object.setProperty("estado","ok");
        game.addObject(object);

        Condition conditionOk = new ConditionPropertyEquals(
                new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("ok")
        );

        Condition conditionFail = new ConditionPropertyEquals(
                new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("fail")
        );

        assertTrue(conditionOk.check(game));
        assertFalse(conditionFail.check(game));
    }

}