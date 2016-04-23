package ar.fiuba.tdd.tp.input.converter;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.config.ConnectorSettings;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.command.client.ConnectCommand;

import java.util.ArrayList;

public class ClientCommandConverter extends AbstractCommandConverter {

    public ClientCommandConverter() {
        super(new ArrayList<String>() { {
                add("^Nuevo juego");
                add("^dobla a la derecha gil");
            }
        });
    }

    protected InputCommand doConvert(String input) {
        //TODO parsear el input y enviar el ConnectCommand!
        return new ConnectCommand(new ClientV2(null, null, null), new ConnectorSettings("", 0, 0L, 0L));
    }

}
