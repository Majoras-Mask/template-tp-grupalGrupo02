package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.ObjectInterface;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueGetDescriptionTest {
    @Test
    public void test() {
        ObjectInterface object = new ObjectConcrete("item");
        Context context = new Context() {
            @Override
            public ObjectInterface getObject(String name) {
                return object;
            }
        };

        ValueGetDescription value = new ValueGetDescription(new ValueConstant("(objeto"));

        assertEquals("item", value.getValue(context));
    }
}