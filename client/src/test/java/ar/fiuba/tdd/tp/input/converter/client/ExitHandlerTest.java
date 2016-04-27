package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.client.ExitRequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ExitHandlerTest {

    private final ClientCore mock = mock(ClientCore.class);
    private final ClientRequestHandlerFactory factory = new ClientRequestHandlerFactory(this.mock);
    private ExitRequestHandler handler;

    @Before
    public void setUp() {
        this.handler = this.factory.createExitHandler();
    }

    @Test
    public void shouldMatch() {
        Assert.assertTrue(this.handler.match(newRequest("Exit game")));
        Assert.assertTrue(this.handler.match(newRequest("exit game")));
        Assert.assertTrue(this.handler.match(newRequest("exit")));
    }

    @Test
    public void shouldNotMatch() {
        Assert.assertFalse(this.handler.match(newRequest("help me exit")));
        Assert.assertFalse(this.handler.match(newRequest("exti")));
    }

    private ClientRequest newRequest(String input) {
        return new ClientRequest(input);
    }

}