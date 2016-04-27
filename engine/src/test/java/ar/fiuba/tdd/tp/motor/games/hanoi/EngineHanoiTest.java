package ar.fiuba.tdd.tp.motor.games.hanoi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 23/04/16.
 */
public class EngineHanoiTest {

    @Test
    public void testHelper() throws Exception {
        EngineHanoi engine = new EngineHanoi();
        String response = engine.request("What can i do with stack 1");
        assertEquals("you can check top/move top" , response.toLowerCase());
    }

    @Test
    public void testCheckSize() throws Exception {
        EngineHanoi engine = new EngineHanoi();
        String response = engine.request("check top stack 1");
        assertEquals("size of top from stack 1 is 1" , response.toLowerCase());
    }

    @Test
    public void testMove() throws Exception {
        EngineHanoi engine = new EngineHanoi();
        String response = engine.request("move top stack 1 stack 2");
        assertEquals("moved!" , response.toLowerCase());

        response = engine.request("check top stack 1");
        assertEquals("size of top from stack 1 is 2" , response.toLowerCase());
    }

}