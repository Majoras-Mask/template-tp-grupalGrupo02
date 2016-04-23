
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;
import ar.fiuba.tdd.tp.config.ConnectorSettings;

import java.util.Objects;

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

    public ConnectorSettings getSettings() {
        return settings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConnectCommand that = (ConnectCommand) o;
        return Objects.equals(settings, that.settings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), settings);
    }
}
