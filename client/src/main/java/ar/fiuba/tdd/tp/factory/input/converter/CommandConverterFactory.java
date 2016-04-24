package ar.fiuba.tdd.tp.factory.input.converter;

import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.client.ClientCommandConverterFactory;
import ar.fiuba.tdd.tp.factory.input.converter.game.GameCommandConverterFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandConverterFactory {

    private final ClientCommandConverterFactory clientCommandConverterFactory;
    private final GameCommandConverterFactory gameCommandConverterFactory;

    public CommandConverterFactory(CommandProcessor commandProcessor) {
        this.clientCommandConverterFactory = new ClientCommandConverterFactory(commandProcessor);
        this.gameCommandConverterFactory = new GameCommandConverterFactory(commandProcessor);
    }

    public AbstractCommandConverter createConverters() {
        List<AbstractCommandConverter> clientConverters = this.clientCommandConverterFactory.createClientConverters();
        List<AbstractCommandConverter> gameConverters = this.gameCommandConverterFactory.createGameConverters();

        return chainConverters(clientConverters, gameConverters);
    }

    private AbstractCommandConverter chainConverters(List<AbstractCommandConverter> clientConverters,
                                                           List<AbstractCommandConverter> gameConverters) {
        final List<AbstractCommandConverter> allConverters = new ArrayList<>();

        allConverters.addAll(clientConverters);
        allConverters.addAll(gameConverters);

        return chainConverters(allConverters);
    }

    private AbstractCommandConverter chainConverters(List<AbstractCommandConverter> allConverters) {
        final Iterator<AbstractCommandConverter> iterator = allConverters.iterator();
        final AbstractCommandConverter response = iterator.next();

        while (iterator.hasNext()) {
            response.setNextConverter(iterator.next());
        }

        return response;
    }
}
