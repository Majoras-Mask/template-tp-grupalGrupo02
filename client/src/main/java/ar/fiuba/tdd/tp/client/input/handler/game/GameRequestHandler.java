package ar.fiuba.tdd.tp.client.input.handler.game;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;

import java.util.ArrayList;

public class GameRequestHandler extends AbstractRequestHandler {

    private static final String ANYTHING = ".*";

    public GameRequestHandler(ClientCore core) {
        super(core, new ArrayList<String>() { {
                add(ANYTHING);
            }
        });
    }

    public void handle(ClientRequest request) {
        this.core.send(request);
    }

    @Override
    public Boolean supports(ClientRequest request) {
        return this.match(request);
    }

}
