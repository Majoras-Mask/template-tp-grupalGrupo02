package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;

import static ar.fiuba.tdd.tp.client.utils.Constants.CLOSE_SUCCESSFUL;

public class ExitRequestHandler extends AbstractRequestHandler {

    public ExitRequestHandler(Client client, RequestHandler nextConverter) {
        super(client, new ArrayList<String>() { {
                add("(?i)^exit");
            }
        }, nextConverter);
    }

    @Override
    protected Optional<ClientResponse> doHandle(ClientRequest request) {
        this.client.stop();
        return Optional.of(CLOSE_SUCCESSFUL);
    }
}
