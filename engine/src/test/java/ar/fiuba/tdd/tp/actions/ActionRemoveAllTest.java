package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionRemoveAllTest {
    @Test
    public void execute() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("objeto");
        ObjectConcrete objectToAdd = new ObjectConcrete("objetoAAgregar");
        game.addObject(object);
        game.addObject(objectToAdd);

        ActionRemoveAll action = new ActionRemoveAll(new ValueConstant("objeto"));
        action.execute(game);

        assertEquals(0, object.getSize());

    }

}