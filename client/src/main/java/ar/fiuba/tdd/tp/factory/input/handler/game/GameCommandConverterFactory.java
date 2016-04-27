package ar.fiuba.tdd.tp.factory.input.handler.game;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.game.GameRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class GameCommandConverterFactory {

    private final ClientCore core;

    public GameCommandConverterFactory(ClientCore core) {
        this.core = core;
    }

    public List<RequestHandler> createGameHandlers() {
        final RequestHandler exitHandler = createGameHandler();

        return new ArrayList<RequestHandler>() { {
                add(exitHandler);
            }
        };
    }

    private RequestHandler createGameHandler() {
        return new GameRequestHandler(this.core);
    }


}
