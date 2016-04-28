package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import static ar.fiuba.tdd.tp.client.utils.Constants.*;
import static java.util.Objects.nonNull;

public class ClientCore {

    private Boolean running;
    private Boolean connected;
    private Connector connector;

    public ClientCore() {
        this.running = Boolean.FALSE;
        this.connected = Boolean.FALSE;
    }

    public Boolean isRunning() {
        return this.running;
    }

    public void start() {
        this.running = Boolean.TRUE;
    }

    public void stop() {
        this.running = Boolean.FALSE;
    }

    public ClientResponse connect(ConnectorSettings settings) {
        if (!isConnected()) {
            this.connected = Boolean.TRUE;
            this.connector = new Connector(settings);
            return new ClientResponse(CONNECTION_SUCCESSFUL);
        }
        throw new ClientException(ANOTHER_OPEN_CONNECTION);
    }

    public ClientResponse sendAndReceive(ClientRequest request) {
        if (nonNull(this.connector)) {
            this.connector.send(new Request(request.getInput()));
            return new ClientResponse(this.connector.receive().getSomething());
        }
        throw new ClientException(OPEN_CONNECTION_FIRST);
    }

    public void stopConnector() {
        if (nonNull(this.connector)) {
            this.connector.close();
            this.connected = Boolean.FALSE;
        }
    }

    public Boolean isConnected() {
        return this.connected;
    }
}
