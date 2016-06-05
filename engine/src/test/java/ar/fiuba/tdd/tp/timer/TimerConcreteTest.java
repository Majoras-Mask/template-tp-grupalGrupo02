package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.actions.Action;
import ar.fiuba.tdd.tp.actions.ActionSetProperty;
import ar.fiuba.tdd.tp.actions.ActionSetPropertyTest;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.conditions.ConditionAlwaysTrue;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 05/06/16.
 */
public class TimerConcreteTest implements Sender{

    private String message = "";

    @Test
    public void testTimer() {
        GameConcrete game = new GameConcrete();
        game.setSender(this);
        ObjectInterface objeto = new ObjectConcrete("objeto");
        objeto.setProperty("estado", "ninguno");
        game.addObject(objeto);

        TimerConcrete timer = new TimerConcrete(1);
        Condition condition = new ConditionAlwaysTrue();
        Action action = new ActionSetProperty(new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("bueno"));
        String responseRequired = "Se cambio el estado del objeto";

        timer.setCondition(condition, action, responseRequired);

        game.addTimer(timer);

        assertNotEquals(message, responseRequired);
        assertNotEquals(objeto.getProperty("estado"), "bueno");

        game.update(); // Un tick solo.
        assertEquals(responseRequired, message);
        assertEquals("bueno", objeto.getProperty("estado"));

    }

    @Override
    public void sendAll(String message) {
        this.message = message;
    }

    @Override
    public void send(String playerID, String message) {
        return;
    }
}