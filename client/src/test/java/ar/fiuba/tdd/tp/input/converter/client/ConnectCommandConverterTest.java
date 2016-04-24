package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.input.command.client.ConnectCommand;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ConnectCommandConverterTest {

    private CommandProcessor commandProcessor;
    private ConnectCommandConverter connectCommandConverter;

    @Before
    public void setUp() {
        this.commandProcessor = mock(CommandProcessor.class);
        this.connectCommandConverter = new ConnectCommandConverter(this.commandProcessor);
    }

    @Test
    public void testDoConvert() throws Exception {
        final ConnectCommand command1 = this.connectCommandConverter.doConvert("connect 127.0.0.1:8080");
//        assertEquals(command1, newExpected(this.commandProcessor, "127.0.0.1", 8080));

        final ConnectCommand command2 = this.connectCommandConverter.doConvert("connect 127.0.0.1:16573");
//        assertEquals(command2, newExpected(this.commandProcessor, "127.0.0.1", 16573));

        final ConnectCommand command3 = this.connectCommandConverter.doConvert("connect 192.23.1.3:40394");
//        assertEquals(command3, newExpected(this.commandProcessor, "192.23.1.3", 40394));
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

    private ConnectCommand newExpected(CommandProcessor commandProcessor, String host, int port) {
        return new ConnectCommand(commandProcessor, new ConnectorSettings(host, port));
    }

}