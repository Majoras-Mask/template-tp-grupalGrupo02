package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RequestHandlerResolverTest {

    private static final RequestHandler handler1 = Mockito.mock(RequestHandler.class);
    private static final RequestHandler handler2 = Mockito.mock(RequestHandler.class);
    private static final RequestHandler handler3 = Mockito.mock(RequestHandler.class);

    private static final ClientRequest supported = new ClientRequest("supported");
    private static final ClientRequest notSupported = new ClientRequest("notSupported");

    private RequestHandlerResolver resolver;

    @Before
    public void setUp() throws Exception {
        this.resolver = new RequestHandlerResolver(createHandlers());
    }

    @Test
    public void testShouldResolveHandler() throws Exception {
        Assert.assertEquals(this.resolver.resolve(supported), handler2);
    }

    @Test(expected = ClientException.class)
    public void testShouldFailOnUnsupported() throws Exception {
        Assert.assertEquals(this.resolver.resolve(notSupported), handler2);
    }


    private List<RequestHandler> createHandlers() {
        Mockito.when(handler1.supports(supported)).thenReturn(Boolean.FALSE);
        Mockito.when(handler2.supports(supported)).thenReturn(Boolean.TRUE);
        Mockito.when(handler3.supports(supported)).thenReturn(Boolean.FALSE);

        Mockito.when(handler1.supports(notSupported)).thenReturn(Boolean.FALSE);
        Mockito.when(handler2.supports(notSupported)).thenReturn(Boolean.FALSE);
        Mockito.when(handler3.supports(notSupported)).thenReturn(Boolean.FALSE);

        return new ArrayList<RequestHandler>() { {
                add(handler1);
                add(handler2);
                add(handler3);
            }
        };
    }
}