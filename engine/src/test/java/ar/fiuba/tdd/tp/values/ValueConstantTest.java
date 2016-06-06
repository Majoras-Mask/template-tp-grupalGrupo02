package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ValueConstantTest {
    @Test
    public void test() {
        String valueString = "Valor";
        ValueConstant value = new ValueConstant(valueString);
        Context context = new Context() {
            @Override
            public ObjectInterface getObject(String name) {
                return null;
            }
        };

        assertEquals(valueString, value.getValue(context));

    }
}