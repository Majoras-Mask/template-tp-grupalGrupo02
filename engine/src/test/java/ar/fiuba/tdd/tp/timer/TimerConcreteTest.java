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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 05/06/16.
 */
public class TimerConcreteTest implements Sender{

    private String message = "";
    private GameConcrete game;
    private ObjectInterface objeto;
    private Condition condition;
    private Action action;
    private String responseRequired;

    @Before
    public void setUp() throws Exception {
        game = new GameConcrete();
        game.setSender(this);
        objeto = new ObjectConcrete("objeto");
        objeto.setProperty("estado", "ninguno");
        game.addObject(objeto);
        condition = new ConditionAlwaysTrue();
        action = new ActionSetProperty(new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("bueno"));
        responseRequired = "Se cambio el estado del objeto";
    }

    private void testTimerExpireAction() {
        assertEquals(responseRequired, message);
        assertEquals("bueno", objeto.getProperty("estado"));
    }

    @Test
    public void testTimerConcrete() {
        long ticks = 2;
        TimerConcrete timer = new TimerConcrete(ticks);
        timer.setCondition(condition, action, responseRequired);
        game.addTimer(timer);

        game.update(ticks / 2);
        assertFalse(timer.isFinished());

        game.update(ticks / 2);
        testTimerExpireAction();
        assertTrue(timer.isFinished());
    }

    @Test
    public void testPeriodicTimer() {
        long ticks = 1;
        TimerConcrete timer = new PeriodicTimer(ticks);
        timer.setCondition(condition, action, responseRequired);
        game.addTimer(timer);

        game.update(ticks);
        testTimerExpireAction();
        assertFalse(timer.isFinished());

        objeto.setProperty("estado", "ninguno");
        message = "";
        game.update(ticks);
        testTimerExpireAction();
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