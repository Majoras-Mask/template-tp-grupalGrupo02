package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import static ar.fiuba.tdd.tp.client.utils.Constants.ANOTHER_OPEN_CONNECTION;
import static ar.fiuba.tdd.tp.client.utils.Constants.CONNECTION_SUCCESSFUL;
import static ar.fiuba.tdd.tp.client.utils.Constants.OPEN_CONNECTION_FIRST;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ClientCore {

    private Boolean running;
    private Connector connector;

    public ClientCore() {
        this.running = Boolean.FALSE;
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
        if (isNull(this.connector)) {
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
            this.connector = null;
        }
    }
}
