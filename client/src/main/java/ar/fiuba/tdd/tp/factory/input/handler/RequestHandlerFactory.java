package ar.fiuba.tdd.tp.factory.input.handler;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.factory.input.handler.client.ClientRequestHandlerFactory;
import ar.fiuba.tdd.tp.factory.input.handler.game.GameCommandConverterFactory;

import java.util.List;

public class RequestHandlerFactory {

    private final ClientRequestHandlerFactory clientRequestHandlerFactory;
    private final GameCommandConverterFactory gameCommandConverterFactory;

    public RequestHandlerFactory(ClientCore core) {
        this.clientRequestHandlerFactory = new ClientRequestHandlerFactory(core);
        this.gameCommandConverterFactory = new GameCommandConverterFactory(core);
    }

    public List<RequestHandler> createHandlers() {
        List<RequestHandler> clientConverters = this.clientRequestHandlerFactory.createClientHandlers();
        List<RequestHandler> gameConverters = this.gameCommandConverterFactory.createGameHandlers();

        clientConverters.addAll(gameConverters);

        return clientConverters;
    }

}
