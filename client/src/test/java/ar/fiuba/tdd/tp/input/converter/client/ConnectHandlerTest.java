package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.exception.ConverterException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.client.ConnectRequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ConnectHandlerTest {

    private final Client mock = mock(Client.class);
    private final ClientRequestHandlerFactory factory = new ClientRequestHandlerFactory(this.mock);
    private ConnectRequestHandler handler;

    @Before
    public void setUp() {
        this.handler = this.factory.createConnectHandler();
    }

    @Test
    public void shouldMatch() {
        Assert.assertTrue(this.handler.match(newRequest("connect 127.0.0.1:8080")));
        Assert.assertTrue(this.handler.match(newRequest("connect 127.0.0.1:16573")));
        Assert.assertTrue(this.handler.match(newRequest("connect 192.23.1.3:40394")));
    }

    @Test
    public void shouldNotMatch() {
        Assert.assertFalse(this.handler.match(newRequest("connect")));
        Assert.assertFalse(this.handler.match(newRequest("connect 1.0.0.1:")));
        Assert.assertFalse(this.handler.match(newRequest("connect :8080")));
        Assert.assertFalse(this.handler.match(newRequest("connect 1.0.0.1.1:8080")));
        Assert.assertFalse(this.handler.match(newRequest("connect 127.0.0.1:126573")));
    }

    @Test(expected = ConverterException.class)
    public void conversion_should_fail_on_wrong_host() throws Exception {
        this.handler.handle(newRequest("connect 192.23.1:40394"));
    }

    private ClientRequest newRequest(String input) {
        return new ClientRequest(input);
    }

}