package ar.fiuba.tdd.tp.client.input.handler.game;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;

public class GameRequestHandler extends AbstractRequestHandler {

    private static final String ANYTHING = ".*";
    private static final String EXIT = "exit";

    public GameRequestHandler(ClientCore core) {
        super(core, new ArrayList<String>() { {
                add(ANYTHING);
            }
        });
    }

    public Optional<ClientResponse> handle(ClientRequest request) {
        ClientResponse response = this.core.sendAndReceive(request);
        if (isFinish(response)) {
            this.core.stopConnector();
        }
        return Optional.of(response);
    }

    @Override
    public Boolean supports(ClientRequest request) {
        return this.match(request);
    }

    private Boolean isFinish(ClientResponse response) {
        return response.getEvent().equals(EXIT);
    }
}
