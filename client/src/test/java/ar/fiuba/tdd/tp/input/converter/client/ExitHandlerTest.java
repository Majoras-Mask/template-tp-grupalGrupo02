package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.exception.ConverterException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.client.ExitRequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ExitHandlerTest {

    private final Client mock = mock(Client.class);
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

    @Test(expected = ConverterException.class)
    public void conversion_should_fail2() throws Exception {
        this.handler.handle(newRequest("exiiit"));
    }

    private ClientRequest newRequest(String input) {
        return new ClientRequest(input);
    }

}