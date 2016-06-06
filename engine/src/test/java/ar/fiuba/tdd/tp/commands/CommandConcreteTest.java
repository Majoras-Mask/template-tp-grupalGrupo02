package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.actions.ActionNull;
import ar.fiuba.tdd.tp.actions.ActionSetProperty;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.conditions.ConditionAlwaysTrue;
import ar.fiuba.tdd.tp.conditions.ConditionHasItem;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 06/06/16.
 */
public class CommandConcreteTest {

    private static final String NAME_OBJECT = "objeto";
    private static final String COMMAND = "COMMAND PRUEBA";
    private static final String COMMAND_OTHER = "COMMAND OTHER";
    private static final String PROPERTY_OBJECT = "estado";
    private static final String VALUE_PROPERTY_OBJECT = "ok";
    private static final String MESSAGE_COMMAND = "Mensaje de respuesta";

    @Test
    public void testCommand() {
        GameConcrete gameConcrete = new GameConcrete();
        ObjectConcrete object = new ObjectConcrete(NAME_OBJECT);
        gameConcrete.addObject(object);

        CommandConcrete testCommand = new CommandConcrete(COMMAND);;
        testCommand.setCondition(
                new ConditionAlwaysTrue(),
                new ActionSetProperty(new ValueConstant(NAME_OBJECT), new ValueConstant(PROPERTY_OBJECT),
                        new ValueConstant(VALUE_PROPERTY_OBJECT)),
                MESSAGE_COMMAND
        );

        assertTrue(testCommand.matches(COMMAND));
        assertFalse(testCommand.matches(COMMAND_OTHER));

        testCommand.execute(COMMAND_OTHER, gameConcrete);
        assertNotEquals(object.getProperty(PROPERTY_OBJECT), VALUE_PROPERTY_OBJECT);
        String response = testCommand.execute(COMMAND, gameConcrete);
        assertEquals(object.getProperty(PROPERTY_OBJECT), VALUE_PROPERTY_OBJECT);
        assertEquals(MESSAGE_COMMAND, response);
    }
}