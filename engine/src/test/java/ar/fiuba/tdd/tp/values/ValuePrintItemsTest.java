package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.ObjectInterface;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 15/06/16.
 */
public class ValuePrintItemsTest {
    @Test
    public void test() {
        ObjectInterface objectInterface = new ObjectConcrete("objeto");
        Context context = new Context() {
            @Override
            public ObjectInterface getObject(String name) {
                return objectInterface;
            }
        };

        Value value = new ValuePrintItems(new ValueConstant("objeto"));

        assertEquals("Empty.", value.getValue(context));

        ObjectInterface item1 = new ObjectConcrete("item1");
        objectInterface.add(item1);
        assertEquals("item1.", value.getValue(context));

        ObjectInterface item2 = new ObjectConcrete("item2");
        objectInterface.add(item2);
        assertEquals("item1 , item2.", value.getValue(context));
    }

}