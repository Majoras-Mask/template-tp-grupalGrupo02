package ar.fiuba.tdd.tp.client.input.handler.game;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;

public class GameRequestHandler extends AbstractRequestHandler {


    private static final String ANYTHING = ".*";

    public GameRequestHandler(Client client, RequestHandler nextConverter) {
        super(client, new ArrayList<String>() { {
                add(ANYTHING);
            }
        }, nextConverter);
    }

    @Override
    protected Optional<ClientResponse> doHandle(ClientRequest request) {
        return Optional.of(this.client.sendAndReceive(request));
    }
}
