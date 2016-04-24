package ar.fiuba.tdd.tp.factory.input.converter;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.client.ClientCommandConverterFactory;
import ar.fiuba.tdd.tp.factory.input.converter.game.GameCommandConverterFactory;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

public class CommandConverterFactory {

    private final ClientCommandConverterFactory clientCommandConverterFactory;
    private final GameCommandConverterFactory gameCommandConverterFactory;

    public CommandConverterFactory(CommandProcessor commandProcessor) {
        this.clientCommandConverterFactory = new ClientCommandConverterFactory(commandProcessor);
        this.gameCommandConverterFactory = new GameCommandConverterFactory(commandProcessor);
    }

    public AbstractCommandConverter createConverters() {
        AbstractCommandConverter clientConverters = this.clientCommandConverterFactory.createClientConverters();
        clientConverters.setNextConverter(this.gameCommandConverterFactory.createGameConverters());

        return clientConverters;
    }
}
