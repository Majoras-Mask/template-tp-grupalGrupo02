
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;

public class ExitCommand extends ClientCommand {

    public ExitCommand(ClientV2 client) {
        super(client);
    }

    @Override
    public void execute() {
        this.client.close();
    }
}
