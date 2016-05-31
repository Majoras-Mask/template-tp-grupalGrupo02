package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.values.Value;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionAddObjectTest {
    @Test
    public void doOperationOnObjects() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        ObjectConcrete objectToAdd = new ObjectConcrete("objetoAAgregar");
        game.addObject(object);
        game.addObject(objectToAdd);

        ActionAddObject action = new ActionAddObject(new ValueConstant("objeto"), new ValueConstant("objetoAAgregar"));

        action.execute(game);

        assertTrue(object.hasObject(objectToAdd));
    }

}