package ar.fiuba.tdd.tp.input.converter;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.InputCommand;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public abstract class AbstractCommandConverter implements InputConverter {

    protected InputConverter nextConverter;
    protected final List<Pattern> patterns;
    protected final CommandProcessor commandProcessor;

    public AbstractCommandConverter(CommandProcessor commandProcessor, List<String> regexList) {
        if (isNull(regexList) || regexList.isEmpty()) {
            throw new IllegalArgumentException("RegexList can't be null or empty");
        }

        this.patterns = regexList.stream().map(Pattern::compile).collect(toList());
        this.commandProcessor = requireNonNull(commandProcessor);
    }

    public InputCommand convert(String input) {
        if (this.match(input)) {
            return doConvert(input);
        }
        if (this.getNextConverter() != null) {
            this.getNextConverter().convert(input);
        }
        throw new IllegalStateException("Can not handle input " + input);
    }

    protected Boolean match(String string) {
        return this.patterns.stream().anyMatch(pattern -> pattern.matcher(string).find());
    }

    public InputConverter getNextConverter() {
        return nextConverter;
    }

    public void setNextConverter(InputConverter nextConverter) {
        this.nextConverter = nextConverter;
    }

    protected abstract InputCommand doConvert(String input);

}
