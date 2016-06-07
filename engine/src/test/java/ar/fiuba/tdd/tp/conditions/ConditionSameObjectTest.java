package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ConditionSameObjectTest {
    @Test
    public void check() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object1 = new ObjectConcrete("objeto1");
        ObjectConcrete object2 = new ObjectConcrete("objeto2");
        game.addObject(object1);
        game.addObject(object1);

        Condition conditionOk = new ConditionSameObject(new ValueConstant("objeto1"), new ValueConstant("objeto1"));
        Condition conditionFail = new ConditionSameObject(new ValueConstant("objeto1"), new ValueConstant("objeto2"));

        assertTrue(conditionOk.check(game));
        assertFalse(conditionFail.check(game));
    }

    @Test
    public void checkWithRegex() throws Exception {
        ObjectConcrete object1 = new ObjectConcrete("objeto1");
        Context context = new Context() {
            @Override
            public ObjectInterface getObject(String name) {
                if (name.equals("(objeto)")) {
                    return object1;
                } else if (name.equals("objeto1")) {
                    return object1;
                }
                return ObjectNull.getInstance();
            }
        };

        Condition conditionOk = new ConditionSameObject(new ValueConstant("objeto1"), new ValueConstant("(objeto)"));

        assertTrue(conditionOk.check(context));

    }

}