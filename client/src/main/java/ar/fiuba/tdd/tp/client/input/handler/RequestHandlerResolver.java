package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class RequestHandlerResolver {

    private List<RequestHandler> handlers;

    public RequestHandlerResolver(List<RequestHandler> handlers) {
        this.handlers = requireNonNull(handlers, "Handlers can't be null");
    }

    public RequestHandler resolve(ClientRequest request) {
        Optional<RequestHandler> first = this.handlers.stream()
                .filter(handler -> handler.supports(request))
                .findFirst();

        if (first.isPresent()) {
            return first.get();
        }
        throw new ClientException("No handler for input " + request.getInput());
    }

}
