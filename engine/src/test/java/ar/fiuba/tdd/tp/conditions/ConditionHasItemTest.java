package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ConditionHasItemTest {
    @Test
    public void check() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete objeto = new ObjectConcrete("objeto");
        ObjectConcrete item = new ObjectConcrete("item");
        game.addObject(objeto);
        game.addObject(item);

        Condition condition = new ConditionHasItem(new ValueConstant("objeto"), new ValueConstant("item"));

        assertFalse(condition.check(game));

        objeto.add(item);
        assertTrue(condition.check(game));
    }

}