package ar.fiuba.tdd.tp.factory.input.handler.game;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.game.GameRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class GameCommandConverterFactory {

    private final Client client;

    public GameCommandConverterFactory(Client client) {
        this.client = client;
    }

    public List<AbstractRequestHandler> createGameConverters() {
        final AbstractRequestHandler exitHandler = createExitHandler();

        return new ArrayList<AbstractRequestHandler>() { {
                add(exitHandler);
            }
        };
    }

    private AbstractRequestHandler createExitHandler() {
        return new GameRequestHandler(this.client, null);
    }


}
