package ar.fiuba.tdd.tp.factory.input.converter.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.input.converter.client.ConnectCommandConverter;
import ar.fiuba.tdd.tp.input.converter.client.ExitCommandConverter;
import ar.fiuba.tdd.tp.input.converter.client.HelpCommandConverter;

public class ClientCommandConverterFactory {

    private final CommandProcessor commandProcessor;

    public ClientCommandConverterFactory(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public AbstractCommandConverter createClientConverters() {
        return this.createExitCommandConverter(this.createConnectCommandConverter(this.createHelpCommandConverter()));
    }

    public ConnectCommandConverter createConnectCommandConverter() {
        return new ConnectCommandConverter(this.commandProcessor);
    }

    public ConnectCommandConverter createConnectCommandConverter(AbstractCommandConverter next) {
        final ConnectCommandConverter response = this.createConnectCommandConverter();
        response.setNextConverter(next);
        return response;
    }

    public ExitCommandConverter createExitCommandConverter() {
        return new ExitCommandConverter(this.commandProcessor);
    }

    public ExitCommandConverter createExitCommandConverter(AbstractCommandConverter next) {
        final ExitCommandConverter response = this.createExitCommandConverter();
        response.setNextConverter(next);
        return response;
    }

    public HelpCommandConverter createHelpCommandConverter() {
        return new HelpCommandConverter(this.commandProcessor);
    }

    public HelpCommandConverter createHelpCommandConverter(AbstractCommandConverter next) {
        final HelpCommandConverter response = createHelpCommandConverter();
        response.setNextConverter(next);
        return response;
    }

}
