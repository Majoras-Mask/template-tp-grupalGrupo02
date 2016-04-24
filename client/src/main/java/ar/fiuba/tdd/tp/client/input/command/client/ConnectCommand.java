
package ar.fiuba.tdd.tp.client.input.command.client;

import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import static java.util.Objects.requireNonNull;

public class ConnectCommand extends ClientCommand {

    private final ConnectorSettings settings;

    public ConnectCommand(CommandProcessor commandProcessor, ConnectorSettings settings) {
        super(commandProcessor);
        this.settings = requireNonNull(settings);
    }

    @Override
    public ClientResponse execute() {
        return this.commandProcessor.connect(settings);
    }

    public ConnectorSettings getSettings() {
        return settings;
    }

}
