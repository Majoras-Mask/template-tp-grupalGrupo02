package ar.fiuba.tdd.tp.server.communication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by manuelcruz on 28/04/2016.
 */
public class RequestTest {

    private Request request;
    private Request exitRequest;

    @Before
    public void init() {
        request = new Request("message");
        exitRequest = new Request("exit game");
    }

    @Test
    public void getAndSetContentTest() {
        request.setSomething("newMessage");
        assertEquals("newMessage", request.getSomething());
    }

    @Test
    public void isExitFalseTest() {
        assertFalse(request.isExit());
    }

    @Test
    public void isExitTrueTest() {
        assertTrue(exitRequest.isExit());
    }
}