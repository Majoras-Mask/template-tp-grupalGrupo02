package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionSetPropertyTest {
    @Test
    public void execute() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("object");
        game.addObject(object);
        ActionSetProperty action = new ActionSetProperty(new ValueConstant("object"), new ValueConstant("estado"),
                new ValueConstant("cerrado"));

        action.execute(game);

        assertEquals("cerrado", object.getProperty("estado"));
    }

}