package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.client.HelpCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.client.HelpCommandConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.client.ClientCommandConverterFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class HelpCommandConverterTest {

    private final CommandProcessor mock = mock(CommandProcessor.class);
    private final ClientCommandConverterFactory factory = new ClientCommandConverterFactory(this.mock);
    private HelpCommandConverter converter;

    @Before
    public void setUp() {
        this.converter = this.factory.createHelpCommandConverter();
    }

    @Test
    public void testDoConvert() throws Exception {
        final HelpCommand command1 = (HelpCommand) this.converter.convert(newRequest("help game1"));
        final HelpCommand command2 = (HelpCommand) this.converter.convert(newRequest("help help"));

        Assert.assertEquals(command1.getGameName(), "game1");
        Assert.assertEquals(command2.getGameName(), "help");
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail() throws Exception {
        this.converter.convert(newRequest("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail2() throws Exception {
        this.converter.convert(newRequest("hepl game"));
    }

    private ClientRequest newRequest(String input) {
        return new ClientRequest(input);
    }

}