
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.connector.config.ConnectorSettings;

public class ConnectCommand extends ClientCommand {

    private final ConnectorSettings settings;

    public ConnectCommand(Client client, ConnectorSettings settings) {
        super(client);
        this.settings = settings;
    }

    @Override
    public void execute() {
        this.client.connect(settings);
    }

    public ConnectorSettings getSettings() {
        return settings;
    }

}
