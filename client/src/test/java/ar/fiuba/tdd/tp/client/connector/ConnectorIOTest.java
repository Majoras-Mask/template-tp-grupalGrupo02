package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.client.exception.ConnectorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;

import static org.mockito.Mockito.verify;

public class ConnectorIOTest {

    private final static ObjectOutput OUTPUT_STREAM = Mockito.mock(ObjectOutput.class);
    private final static ObjectInput INPUT_STREAM = Mockito.mock(ObjectInput.class);
    private ConnectorIO connectorIO;

    @Before
    public void setUp() {
        this.connectorIO = new ConnectorIO(OUTPUT_STREAM, INPUT_STREAM);
    }

    @Test
    public void testSend() throws Exception {
        this.connectorIO.send(new Request("test"));
        verify(OUTPUT_STREAM).writeObject(Mockito.any(Request.class));
    }

    @Test
    public void testReceive() throws Exception {
        this.connectorIO.receive();
        verify(INPUT_STREAM).readObject();
    }

    @Test
    public void testClose() throws Exception {
        this.connectorIO.close();
        verify(INPUT_STREAM).close();
        verify(OUTPUT_STREAM).close();
    }

    @Test(expected = ConnectorException.class)
    public void should_fail_on_null_request() throws IOException {
        this.connectorIO.send(null);
    }
}