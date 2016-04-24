package ar.fiuba.tdd.tp.client.input.command.converter.client;

import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;

public class ExitCommandConverter extends AbstractCommandConverter {

    public ExitCommandConverter(CommandProcessor commandProcessor, RequestConverter nextConverter) {
        super(commandProcessor, new ArrayList<String>() { {
                add("^exit$");
            }
        }, nextConverter);
    }

    protected Command doConvert(String input) {
        //There is nothing to do with the input, just close the commandProcessor.
        return new ExitCommand(this.commandProcessor);
    }

}
