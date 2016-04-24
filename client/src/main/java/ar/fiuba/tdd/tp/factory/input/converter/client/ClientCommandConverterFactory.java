package ar.fiuba.tdd.tp.factory.input.converter.client;

import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.client.ConnectCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.client.ExitCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.client.HelpCommandConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;
import java.util.List;

public class ClientCommandConverterFactory {

    private final CommandProcessor commandProcessor;

    public ClientCommandConverterFactory(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public List<AbstractCommandConverter> createClientConverters() {
        final AbstractCommandConverter connectConverter = createConnectCommandConverter();
        final AbstractCommandConverter exitConverter = createExitCommandConverter();
        final AbstractCommandConverter helpConverter = createHelpCommandConverter();

        return new ArrayList<AbstractCommandConverter>() { {
                add(connectConverter);
                add(exitConverter);
                add(helpConverter);
            }
        };
    }

    public ConnectCommandConverter createConnectCommandConverter() {
        return new ConnectCommandConverter(this.commandProcessor, null);
    }

    public ExitCommandConverter createExitCommandConverter() {
        return new ExitCommandConverter(this.commandProcessor, null);
    }

    public HelpCommandConverter createHelpCommandConverter() {
        return new HelpCommandConverter(this.commandProcessor, null);
    }

}
