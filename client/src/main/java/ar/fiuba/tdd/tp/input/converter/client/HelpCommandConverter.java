package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.client.HelpCommand;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

import java.util.ArrayList;

public class HelpCommandConverter extends AbstractCommandConverter {

    private static final String HELP = "help ";

    public HelpCommandConverter(CommandProcessor commandProcessor) {
        super(commandProcessor, new ArrayList<String>() { {
                add("^" + HELP);
            }
        });
    }

    protected HelpCommand doConvert(String input) {
        return new HelpCommand(this.commandProcessor, input.replaceFirst(HELP, ""));
    }

}
