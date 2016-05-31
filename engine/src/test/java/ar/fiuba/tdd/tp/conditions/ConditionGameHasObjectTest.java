package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionGameHasObjectTest {
    @Test
    public void positiveTest() {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        game.addObject(object);

        ConditionGameHasObject condition = new ConditionGameHasObject(new ValueConstant("objeto"));

        assertTrue(condition.check(game));
    }

    @Test
    public void negativeTest() {
        GameConcrete game = new GameConcrete();

        ConditionGameHasObject condition = new ConditionGameHasObject(new ValueConstant("objeto"));

        assertFalse(condition.check(game));
    }

}