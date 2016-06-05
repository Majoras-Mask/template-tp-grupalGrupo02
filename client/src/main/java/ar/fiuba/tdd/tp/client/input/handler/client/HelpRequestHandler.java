package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.utils.Constants;

import java.util.ArrayList;

public class HelpRequestHandler extends AbstractClientRequestHandler {

    private static final String HELP = "help";

    public HelpRequestHandler(ClientCore core) {
        super(core, new ArrayList<String>() { {
                add("(?i)^" + HELP);
            }
        });
    }

    public void handle(ClientRequest request) {
        this.core.notifyListeners(new ClientResponse(Constants.HELP));
    }

}
