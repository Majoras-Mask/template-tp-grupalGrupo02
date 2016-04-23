
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.input.command.InputCommand;

public abstract class ClientCommand implements InputCommand {

    protected final Client client;

    public ClientCommand(Client client) {
        this.client = client;
    }

}
