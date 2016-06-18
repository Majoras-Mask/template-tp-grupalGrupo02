package ar.fiuba.tdd.tp;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectConcreteTest {
    @Test
    public void testAdd() {
        ObjectInterface objectInterface = new ObjectConcrete("object");
        ObjectInterface otherObject = new ObjectConcrete("other");

        assertEquals(0, objectInterface.getSize());
        objectInterface.add(otherObject);
        assertEquals(1, objectInterface.getSize());

        assertTrue(objectInterface.hasObject(otherObject));
    }

    @Test
    public void testRemove() {
        ObjectInterface object = new ObjectConcrete("object");
        ObjectInterface otherObject = new ObjectConcrete("otherObject");

        object.add(otherObject);
        assertEquals(1, object.getSize());
        object.remove(otherObject);
        assertEquals(0, object.getSize());

        assertFalse(object.hasObject(otherObject));
    }

    @Test
    public void testRemoveAll() {
        ObjectInterface object = new ObjectConcrete("object");
        ObjectInterface otherObject = new ObjectConcrete("otherObject");
        ObjectInterface otherObject2 = new ObjectConcrete("otherObject2");

        object.add(otherObject);
        object.add(otherObject2);
        assertEquals(2, object.getSize());
        object.removeAll();
        assertEquals(0, object.getSize());
    }

    @Test
    public void testPropertys() {
        ObjectInterface object = new ObjectConcrete("object");
        String propertyUsed = "property-used";
        String valuePropertyUsed = "value-property-used";
        String propertyNotUsed = "property-not-used";

        object.setProperty(propertyUsed, valuePropertyUsed);
        assertEquals(valuePropertyUsed, object.getProperty(propertyUsed));

        assertEquals("", object.getProperty(propertyNotUsed));
    }
}