
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.input.command.InputCommand;

public abstract class ClientCommand implements InputCommand {

    protected final ClientV2 client;

    public ClientCommand(ClientV2 client) {
        this.client = client;
    }

}
