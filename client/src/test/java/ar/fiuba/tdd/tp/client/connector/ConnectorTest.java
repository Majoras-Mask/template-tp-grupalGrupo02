package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.client.exception.ConnectorException;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ConnectorTest {

    private static final Socket SOCKET = mock(Socket.class);
    private static final ConnectorIO CONNECTOR_IO = mock(ConnectorIO.class);

    private Connector connector;

    @Before
    public void setUp() {
        this.connector = new Connector(SOCKET, CONNECTOR_IO);
    }

    @Test
    public void should_call_connector_to_send() throws Exception {
        this.connector.send(new Request("test"));
        verify(CONNECTOR_IO).send(any(Request.class));
    }

    @Test
    public void should_call_connector_to_receive() throws Exception {
        this.connector.receive();
        verify(CONNECTOR_IO).receive();
    }

    @Test(expected = ConnectorException.class)
    public void should_wrap_exception_on_receive() throws Exception {
        when(CONNECTOR_IO.receive()).thenThrow(new IllegalArgumentException());
        this.connector.receive();
    }

    @Test
    public void testClose() throws Exception {
        this.connector.close();
        verify(CONNECTOR_IO).close();
        verify(SOCKET).close();
    }
}