
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.input.command.InputCommand;

import java.util.Objects;

public abstract class ClientCommand implements InputCommand {

    protected final ClientV2 client;

    public ClientCommand(ClientV2 client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCommand that = (ClientCommand) o;
        return Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }
}
