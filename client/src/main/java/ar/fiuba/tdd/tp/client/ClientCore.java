package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.connector.ConnectorIO;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.exception.ConnectorException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;

import java.io.*;
import java.net.Socket;

import static ar.fiuba.tdd.tp.client.utils.Constants.*;

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
        if (!this.isConnected()) {
            this.connected = Boolean.TRUE;
            this.connector = createConnector(settings);
            Response response = this.connector.receive();
            return new ClientResponse(CONNECTION_SUCCESSFUL + response.getSomething());
        }
        throw new ClientException(ANOTHER_OPEN_CONNECTION);
    }

    public ClientResponse sendAndReceive(ClientRequest request) {
        if (this.isConnected()) {
            this.connector.send(new Request(request.getInput()));
            return new ClientResponse(this.connector.receive().getSomething());
        }
        throw new ClientException(OPEN_CONNECTION_FIRST);
    }

    public void stopConnector() {
        if (this.isConnected()) {
            this.connector.close();
            this.connected = Boolean.FALSE;
        }
    }

    public Boolean isConnected() {
        return this.connected;
    }

    private Connector createConnector(ConnectorSettings settings) {
        try {
            final Socket socket = createSocket(settings);
            return new Connector(socket, createIO(socket));
        } catch (IOException e) {
            throw new ConnectorException(OPEN_ERROR);
        }
    }

    private Socket createSocket(ConnectorSettings settings) throws IOException {
        return new Socket(settings.getHost(), settings.getPort());
    }

    private ConnectorIO createIO(Socket socket) throws IOException {
        try {
            final ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInput input = new ObjectInputStream(socket.getInputStream());

            return new ConnectorIO(output, input);
        } catch (IOException e) {
            socket.close();
            throw new IllegalStateException();
        }
    }
}
