package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.config.ConnectorSettings;
import ar.fiuba.tdd.tp.input.command.client.ConnectCommand;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ConnectCommandConverterTest {

    private Client client;
    private ConnectCommandConverter connectCommandConverter;

    @Before
    public void setUp() {
        this.client = mock(Client.class);
        this.connectCommandConverter = new ConnectCommandConverter(this.client);
    }

    @Test
    public void testDoConvert() throws Exception {
        final ConnectCommand command1 = this.connectCommandConverter.doConvert("connect 127.0.0.1:8080");
//        assertEquals(command1, newExpected(this.client, "127.0.0.1", 8080));

        final ConnectCommand command2 = this.connectCommandConverter.doConvert("connect 127.0.0.1:16573");
//        assertEquals(command2, newExpected(this.client, "127.0.0.1", 16573));

        final ConnectCommand command3 = this.connectCommandConverter.doConvert("connect 192.23.1.3:40394");
//        assertEquals(command3, newExpected(this.client, "192.23.1.3", 40394));
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail() throws Exception {
        this.connectCommandConverter.convert("test");
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail_on_wrong_host() throws Exception {
        this.connectCommandConverter.convert("connect 192.23.1:40394");
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail_on_wrong_port() throws Exception {
        this.connectCommandConverter.convert("connect 192.23.1.3:403942");
    }

    private ConnectCommand newExpected(Client client, String host, int port) {
        return new ConnectCommand(client, new ConnectorSettings(host, port));
    }

}