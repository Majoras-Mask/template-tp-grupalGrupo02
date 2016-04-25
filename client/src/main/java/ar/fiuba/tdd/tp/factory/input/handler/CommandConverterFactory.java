package ar.fiuba.tdd.tp.factory.input.handler;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import ar.fiuba.tdd.tp.factory.input.handler.game.GameCommandConverterFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandConverterFactory {

    private final ClientRequestHandlerFactory clientRequestHandlerFactory;
    private final GameCommandConverterFactory gameCommandConverterFactory;

    public CommandConverterFactory(Client client) {
        this.clientRequestHandlerFactory = new ClientRequestHandlerFactory(client);
        this.gameCommandConverterFactory = new GameCommandConverterFactory(client);
    }

    public AbstractRequestHandler createConverters() {
        List<AbstractRequestHandler> clientConverters = this.clientRequestHandlerFactory.createClientHandlers();
        List<AbstractRequestHandler> gameConverters = this.gameCommandConverterFactory.createGameConverters();

        return chainConverters(clientConverters, gameConverters);
    }

    private AbstractRequestHandler chainConverters(List<AbstractRequestHandler> clientConverters,
                                                   List<AbstractRequestHandler> gameConverters) {
        final List<AbstractRequestHandler> allConverters = new ArrayList<>();

        allConverters.addAll(clientConverters);
        allConverters.addAll(gameConverters);

        return chainConverters(allConverters);
    }

    private AbstractRequestHandler chainConverters(List<AbstractRequestHandler> allConverters) {
        final Iterator<AbstractRequestHandler> iterator = allConverters.iterator();
        final AbstractRequestHandler first = iterator.next();
        AbstractRequestHandler response = first;

        while (iterator.hasNext()) {
            AbstractRequestHandler next = iterator.next();
            response.setNextHandler(next);
            response = next;
        }

        return first;
    }
}
