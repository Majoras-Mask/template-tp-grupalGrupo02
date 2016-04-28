package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;

public class HelpRequestHandler extends AbstractClientRequestHandler {

    private static final String HELP = "help ";

    public HelpRequestHandler(ClientCore core) {
        super(core, new ArrayList<String>() { {
                add("(?i)^" + HELP);
            }
        });
    }

    public Optional<ClientResponse> handle(ClientRequest request) {
        return Optional.of(this.core.sendAndReceive(request));
    }

}
