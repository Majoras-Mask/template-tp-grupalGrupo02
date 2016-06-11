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
    private static final long SECOND = 1;
    private static final long SECOND_TO_MILISECOND = 1000;

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
        TimerConcrete timer = new TimerConcrete(SECOND);
        timer.setCondition(condition, action, responseRequired);
        game.addTimer(timer);

        game.update(SECOND_TO_MILISECOND); // Un tick solo.
        testTimerExpireAction();

        assertTrue(timer.isFinished());
    }

    @Test
    public void testPeriodicTimer() {
        TimerConcrete timer = new PeriodicTimer(SECOND);
        timer.setCondition(condition, action, responseRequired);
        game.addTimer(timer);

        game.update(SECOND_TO_MILISECOND);
        testTimerExpireAction();
        assertFalse(timer.isFinished());

        objeto.setProperty("estado", "ninguno");
        message = "";
        game.update(SECOND_TO_MILISECOND);
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