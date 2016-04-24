package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.client.HelpCommand;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class HelpCommandConverterTest {

    private HelpCommandConverter helpCommandConverter;
    private CommandProcessor commandProcessor;

    @Before
    public void setUp() {
        this.commandProcessor = mock(CommandProcessor.class);
        this.helpCommandConverter = new HelpCommandConverter(this.commandProcessor);
    }

    @Test
    public void testDoConvert() throws Exception {
        final HelpCommand command1 = this.helpCommandConverter.doConvert("help game1");
//        assertEquals(command1, newExpected(this.commandProcessor, "game1"));

        final HelpCommand command2 = this.helpCommandConverter.doConvert("help help");
//        assertEquals(command2, newExpected(this.commandProcessor, "help"));
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail() throws Exception {
        this.helpCommandConverter.convert("test");
    }

    @Test(expected = IllegalStateException.class)
    public void conversion_should_fail_on_wrong_host() throws Exception {
        this.helpCommandConverter.convert("hepl game");
    }

    private HelpCommand newExpected(CommandProcessor commandProcessor, String game1) {
        return new HelpCommand(commandProcessor, game1);
    }

}