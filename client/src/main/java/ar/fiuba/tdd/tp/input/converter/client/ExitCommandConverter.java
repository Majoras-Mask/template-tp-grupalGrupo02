package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

import java.util.ArrayList;

public class ExitCommandConverter extends AbstractCommandConverter {

    public ExitCommandConverter(Client client) {
        super(client, new ArrayList<String>() { {
                add("^exit$");
            }
        });
    }

    protected InputCommand doConvert(String input) {
        //There is nothing to do with the input, just close the client.
        return new ExitCommand(this.client);
    }

}
