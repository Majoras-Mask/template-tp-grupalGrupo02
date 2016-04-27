package ar.fiuba.tdd.tp.factory.input.handler;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import ar.fiuba.tdd.tp.factory.input.handler.game.GameCommandConverterFactory;

import java.util.List;

public class CommandConverterFactory {

    private final ClientRequestHandlerFactory clientRequestHandlerFactory;
    private final GameCommandConverterFactory gameCommandConverterFactory;

    public CommandConverterFactory(ClientCore core) {
        this.clientRequestHandlerFactory = new ClientRequestHandlerFactory(core);
        this.gameCommandConverterFactory = new GameCommandConverterFactory(core);
    }

    public List<AbstractRequestHandler> createConverters() {
        List<AbstractRequestHandler> clientConverters = this.clientRequestHandlerFactory.createClientHandlers();
        List<AbstractRequestHandler> gameConverters = this.gameCommandConverterFactory.createGameConverters();

        clientConverters.addAll(gameConverters);

        return clientConverters;
    }

}
