package ar.fiuba.tdd.tp.client.input.handler;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.ClientRequest;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public abstract class AbstractRequestHandler implements RequestHandler {

    protected final ClientCore core;
    private final List<Pattern> patterns;

    public AbstractRequestHandler(ClientCore core, List<String> regexList) {
        if (isNull(regexList) || regexList.isEmpty()) {
            throw new IllegalArgumentException("RegexList can't be null or empty");
        }

        this.patterns = regexList.stream().map(Pattern::compile).collect(toList());
        this.core = requireNonNull(core);
    }

    public Boolean match(ClientRequest request) {
        return this.patterns.stream().anyMatch(pattern -> pattern.matcher(request.getInput()).find());
    }

}
