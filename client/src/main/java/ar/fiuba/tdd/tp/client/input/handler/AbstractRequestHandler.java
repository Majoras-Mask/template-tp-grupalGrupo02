package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.exception.ConverterException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public abstract class AbstractRequestHandler implements RequestHandler {

    protected final Client client;
    private final List<Pattern> patterns;
    private RequestHandler nextHandler;

    public AbstractRequestHandler(Client client, List<String> regexList, RequestHandler nextHandler) {
        if (isNull(regexList) || regexList.isEmpty()) {
            throw new IllegalArgumentException("RegexList can't be null or empty");
        }

        this.patterns = regexList.stream().map(Pattern::compile).collect(toList());
        this.client = requireNonNull(client);
        this.nextHandler = nextHandler;
    }

    @Override
    public Optional<ClientResponse> handle(ClientRequest request) {
        if (this.match(request)) {
            return doHandle(request);
        }
        if (this.getNextHandler() != null) {
            return this.getNextHandler().handle(request);
        }
        throw new ConverterException("Can not handle input " + request.getInput());
    }

    public Boolean match(ClientRequest request) {
        return this.patterns.stream().anyMatch(pattern -> pattern.matcher(request.getInput()).find());
    }

    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public RequestHandler getNextHandler() {
        return nextHandler;
    }

    protected abstract Optional<ClientResponse> doHandle(ClientRequest request);

}
