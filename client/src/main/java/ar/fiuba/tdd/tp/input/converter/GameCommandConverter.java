package ar.fiuba.tdd.tp.input.converter;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.command.client.ExitCommand;

import java.util.ArrayList;

public class GameCommandConverter extends AbstractCommandConverter {

    public GameCommandConverter(ClientV2 clientV2) {
        super(clientV2, new ArrayList<String>() {
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
