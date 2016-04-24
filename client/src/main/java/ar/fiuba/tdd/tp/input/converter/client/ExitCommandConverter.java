package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

import java.util.ArrayList;

public class ExitCommandConverter extends AbstractCommandConverter {

    public ExitCommandConverter(CommandProcessor commandProcessor) {
        super(commandProcessor, new ArrayList<String>() { {
                add("^exit$");
            }
        });
    }

    protected InputCommand doConvert(String input) {
        //There is nothing to do with the input, just close the commandProcessor.
        return new ExitCommand(this.commandProcessor);
    }

}
