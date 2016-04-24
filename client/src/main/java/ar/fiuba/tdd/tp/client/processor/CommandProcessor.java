package ar.fiuba.tdd.tp.client.processor;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import static ar.fiuba.tdd.tp.client.utils.Constants.CLOSE_SUCCESSFUL;
import static ar.fiuba.tdd.tp.client.utils.Constants.CONNECTION_SUCCESSFUL;
import static java.util.Objects.nonNull;

public class CommandProcessor {

    private final Connector connector;
    private Client client;

    public CommandProcessor(Connector connector) {
        this.connector = connector;
    }

    public ClientResponse connect(ConnectorSettings connectorSettings) {
        try {
            this.connector.init(connectorSettings);
            return CONNECTION_SUCCESSFUL;
        } catch (Exception e) {
            throw new CommandProcessorException("Error while connecting with server " + connectorSettings + ": " + e);
        }
    }

    public ClientResponse close() {
        try {
            this.closeClient();
            this.connector.close();
            return CLOSE_SUCCESSFUL;
        } catch (Exception e) {
            throw new CommandProcessorException("Error while closing connection with server: " + e);
        }
    }

    public ClientResponse sendRequest(Request request) {
        try {
            this.connector.send(request);
            return null;
        } catch (Exception e) {
            throw new CommandProcessorException("Error while sending request " + request + ": " + e);
        }
    }

    public Response getResponse() {
        try {
            return this.connector.receive();
        } catch (Exception e) {
            throw new CommandProcessorException("Error while reading response: " + e);
        }
    }

    public ClientResponse help(String gameName) {
        this.sendRequest(new Request(gameName));
        final Response response = getResponse();
        return new ClientResponse(response.getSomething());
    }

    private void closeClient() {
        if (nonNull(this.client)) {
            this.client.stop();
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
