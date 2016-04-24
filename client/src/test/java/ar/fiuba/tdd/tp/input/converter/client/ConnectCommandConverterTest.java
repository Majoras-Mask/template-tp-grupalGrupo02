package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.client.ConnectCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.client.ConnectCommandConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.client.ClientCommandConverterFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ConnectCommandConverterTest {

    private final CommandProcessor mock = mock(CommandProcessor.class);
    private final ClientCommandConverterFactory factory = new ClientCommandConverterFactory(this.mock);
    private ConnectCommandConverter converter;

    @Before
    public void setUp() {
        this.converter = this.factory.createConnectCommandConverter();
    }

    @Test
    public void testDoConvert() throws Exception {
        final ConnectCommand command1 = (ConnectCommand) this.converter.convert(newRequest("connect 127.0.0.1:8080"));
        final ConnectCommand expected1 = newExpected("127.0.0.1", 8080);

        final ConnectCommand command2 = (ConnectCommand) this.converter.convert(newRequest("connect 127.0.0.1:16573"));
        final ConnectCommand expected2 = newExpected("127.0.0.1", 16573);

        final ConnectCommand command3 = (ConnectCommand) this.converter.convert(newRequest("connect 192.23.1.3:40394"));
        final ConnectCommand expected3 = newExpected("192.23.1.3", 40394);

        assertEquality(command1, expected1);
        assertEquality(command2, expected2);
        assertEquality(command3, expected3);
    }

    private void assertEquality(ConnectCommand command, ConnectCommand expected) {
        Assert.assertEquals(command.getSettings().getHost(), expected.getSettings().getHost());
        Assert.assertEquals(command.getSettings().getPort(), expected.getSettings().getPort());
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail() throws Exception {
        this.converter.convert(newRequest("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail_on_wrong_host() throws Exception {
        this.converter.convert(newRequest("connect 192.23.1:40394"));
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail_on_wrong_port() throws Exception {
        this.converter.convert(newRequest("connect 192.23.1.3:403942"));
    }

    private ConnectCommand newExpected(String host, int port) {
        return new ConnectCommand(this.mock, new ConnectorSettings(host, port));
    }

    private ClientRequest newRequest(String input) {
        return new ClientRequest(input);
    }

}