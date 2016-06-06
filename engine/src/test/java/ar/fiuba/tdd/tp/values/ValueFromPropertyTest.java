package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.ObjectInterface;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ValueFromPropertyTest {
    @Test
    public void test() {
        String objectDescription = "object";
        String property = "estado";
        String valueProperty = "ok";
        ValueFromProperty value = new ValueFromProperty(new ValueConstant(objectDescription), new ValueConstant(property));
        Context context = new Context() {
            @Override
            public ObjectInterface getObject(String name) {
                ObjectConcrete objectInterface = new ObjectConcrete(objectDescription);
                objectInterface.setProperty(property,valueProperty);
                return objectInterface;
            }
        };

        assertEquals(valueProperty, value.getValue(context));

    }

}