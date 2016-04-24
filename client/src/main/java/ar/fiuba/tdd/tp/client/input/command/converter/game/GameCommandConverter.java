package ar.fiuba.tdd.tp.client.input.command.converter.game;

import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;

public class GameCommandConverter extends AbstractCommandConverter {

    public GameCommandConverter(CommandProcessor commandProcessor, RequestConverter nextConverter) {
        super(commandProcessor, new ArrayList<String>() {
                {
                    add("^connect");
                    add("^exit$");
                }
            }, nextConverter);
    }

    //TODO convert the input
    protected Command doConvert(String input) {
        return new ExitCommand(this.commandProcessor);
    }

}
