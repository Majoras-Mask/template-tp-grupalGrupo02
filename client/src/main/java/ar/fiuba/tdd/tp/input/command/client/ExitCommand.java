
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.Client;

public class ExitCommand extends ClientCommand {

    public ExitCommand(Client client) {
        super(client);
    }

    @Override
    public void execute() {
        this.client.close();
    }
}
