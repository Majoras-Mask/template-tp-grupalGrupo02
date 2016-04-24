package ar.fiuba.tdd.tp.input.converter.game;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

import java.util.ArrayList;

public class GameCommandConverter extends AbstractCommandConverter {

    public GameCommandConverter(CommandProcessor commandProcessor) {
        super(commandProcessor, new ArrayList<String>() {
                {
                    add("^connect");
                    add("^exit$");
                }
            });
    }

    //TODO convert the input
    protected InputCommand doConvert(String input) {
        return new ExitCommand(null);
    }

}
