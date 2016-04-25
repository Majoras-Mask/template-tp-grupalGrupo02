package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.Optional;

public interface RequestHandler {
    Optional<ClientResponse> handle(ClientRequest request);
}
