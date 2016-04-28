package ar.fiuba.tdd.tp.server.communication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ResponseTest {
    private Response request;

    @Test
    public void getAndSetContentTest() {
        request = new Response("message");
        request.setSomething("newMessage");
        assertEquals("newMessage", request.getSomething());
    }


}
