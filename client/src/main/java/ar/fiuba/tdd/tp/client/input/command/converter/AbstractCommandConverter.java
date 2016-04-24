package ar.fiuba.tdd.tp.client.input.command.converter;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public abstract class AbstractCommandConverter implements RequestConverter {

    protected final CommandProcessor commandProcessor;

    private final List<Pattern> patterns;
    private RequestConverter nextConverter;

    public AbstractCommandConverter(CommandProcessor commandProcessor, List<String> regexList,
                                    RequestConverter nextConverter) {
        if (isNull(regexList) || regexList.isEmpty()) {
            throw new IllegalArgumentException("RegexList can't be null or empty");
        }

        this.patterns = regexList.stream().map(Pattern::compile).collect(toList());
        this.commandProcessor = requireNonNull(commandProcessor);
        this.nextConverter = nextConverter;
    }

    @Override
    public Command convert(ClientRequest request) {
        if (this.match(request.getInput())) {
            return doConvert(request.getInput());
        }
        if (this.getNextConverter() != null) {
            this.getNextConverter().convert(request);
        }
        throw new IllegalStateException("Can not handle input " + request.getInput());
    }

    protected Boolean match(String string) {
        return this.patterns.stream().anyMatch(pattern -> pattern.matcher(string).find());
    }

    public void setNextConverter(RequestConverter nextConverter) {
        this.nextConverter = nextConverter;
    }

    public RequestConverter getNextConverter() {
        return nextConverter;
    }

    protected abstract Command doConvert(String input);

}
