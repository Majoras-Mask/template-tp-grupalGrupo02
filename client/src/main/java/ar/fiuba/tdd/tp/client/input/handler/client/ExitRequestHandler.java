package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;

import static ar.fiuba.tdd.tp.client.utils.Constants.CLOSE_SUCCESSFUL;

public class ExitRequestHandler extends AbstractRequestHandler {

    public ExitRequestHandler(ClientCore core) {
        super(core, new ArrayList<String>() { {
                add("(?i)^exit");
            }
        });
    }

    public Optional<ClientResponse> handle(ClientRequest request) {
        this.core.stop();
        return Optional.of(CLOSE_SUCCESSFUL);
    }

}
