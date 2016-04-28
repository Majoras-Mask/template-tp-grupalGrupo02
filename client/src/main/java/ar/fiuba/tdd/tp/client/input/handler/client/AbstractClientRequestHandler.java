package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;

import java.util.List;

/**
 * All type of {@link RequestHandler} for the client are disabled while the {@link ClientCore} is connected.
 * Only game request are sent.
 */
public abstract class AbstractClientRequestHandler extends AbstractRequestHandler {

    public AbstractClientRequestHandler(ClientCore core, List<String> regexList) {
        super(core, regexList);
    }

    @Override
    public Boolean supports(ClientRequest request) {
        if (this.core.isConnected()) {
            return Boolean.FALSE;
        }
        return super.match(request);
    }

}
