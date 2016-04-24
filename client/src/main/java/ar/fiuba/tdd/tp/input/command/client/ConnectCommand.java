
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.output.ClientResponse;

public class ConnectCommand extends ClientCommand {

    private final ConnectorSettings settings;

    public ConnectCommand(CommandProcessor commandProcessor, ConnectorSettings settings) {
        super(commandProcessor);
        this.settings = settings;
    }

    @Override
    public ClientResponse execute() {
        return this.commandProcessor.connect(settings);
    }

    public ConnectorSettings getSettings() {
        return settings;
    }

}
