package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.input.ClientRequest;

public interface RequestHandler {

    void handle(ClientRequest request);

    Boolean supports(ClientRequest request);
}
