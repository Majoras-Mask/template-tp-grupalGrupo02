package ar.fiuba.tdd.tp.client.input.command.converter.client;

import ar.fiuba.tdd.tp.client.input.command.client.HelpCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;

public class HelpCommandConverter extends AbstractCommandConverter {

    private static final String HELP = "help ";

    public HelpCommandConverter(CommandProcessor commandProcessor, RequestConverter nextConverter) {
        super(commandProcessor, new ArrayList<String>() { {
                add("^" + HELP);
            }
        }, nextConverter);
    }

    protected HelpCommand doConvert(String input) {
        return new HelpCommand(this.commandProcessor, input.replaceFirst(HELP, ""));
    }

}
