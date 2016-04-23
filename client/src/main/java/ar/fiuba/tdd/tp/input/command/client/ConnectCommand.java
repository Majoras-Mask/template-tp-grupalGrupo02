
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.config.ConnectorSettings;

public class ConnectCommand extends ClientCommand {

    private final ConnectorSettings settings;

    public ConnectCommand(ClientV2 client, ConnectorSettings settings) {
        super(client);
        this.settings = settings;
    }

    @Override
    public void execute() {
        this.client.connect(settings);
    }
}
