package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionRemoveObjectTest {
    @Test
    public void doOperationOnObjects() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        ObjectConcrete objectToAdd = new ObjectConcrete("objetoAAgregar");
        game.addObject(object);
        object.add(objectToAdd);
        game.addObject(objectToAdd);

        ActionRemoveObject action = new ActionRemoveObject(new ValueConstant("objeto"), new ValueConstant("objetoAAgregar"));
        action.execute(game);

        assertFalse(object.hasObject(objectToAdd));
    }

}