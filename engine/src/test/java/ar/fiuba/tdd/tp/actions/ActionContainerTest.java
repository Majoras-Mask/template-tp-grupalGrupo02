package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class ActionContainerTest {
    @Test
    public void execute() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete("object");
        game.addObject(object);
        object.setProperty("estado1", "ninguno");
        object.setProperty("estado2", "ninguno");

        Action action1 = new ActionSetProperty(
                new ValueConstant("object"),new ValueConstant("estado1"), new ValueConstant("ok1")
        );
        Action action2 = new ActionSetProperty(
                new ValueConstant("object"),new ValueConstant("estado2"), new ValueConstant("ok2")
        );

        ActionContainer action = new ActionContainer();
        action.addAction(action1);
        action.addAction(action2);

        action.execute(game);

        assertEquals("ok1", object.getProperty("estado1"));
        assertEquals("ok2", object.getProperty("estado2"));
    }

}