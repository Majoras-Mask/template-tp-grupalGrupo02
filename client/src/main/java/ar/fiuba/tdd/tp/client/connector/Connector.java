package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ConnectorException;

import java.io.IOException;
import java.net.Socket;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class Connector {

    private final Socket socket;
    private final ConnectorIO connectorIO;

    public Connector(ConnectorSettings settings) {
        try {
            this.socket = getNewSocket(settings);
            this.connectorIO = getNewIO();
        } catch (Exception e) {
            throw new ConnectorException("Can't connect with server!");
        }
    }

    private Socket getNewSocket(ConnectorSettings settings) throws IOException {
        if (isNull(settings)) {
            throw new ConnectorException("No connection settings!");
        }

        final String host = requireNonNull(settings.getHost());
        final int port = requireNonNull(settings.getPort());

        return new Socket(host, port);
    }

    private ConnectorIO getNewIO() throws IOException {
        try {
            return new ConnectorIO(this.socket);
        } catch (IOException e) {
            this.socket.close();
            throw new IllegalStateException();
        }
    }

    public void send(Request request) {
        try {
            this.connectorIO.send(request);
        } catch (IOException e) {
            throw new ConnectorException("Can't connect with server");
        }
    }

    public Response receive() {
        try {
            return this.connectorIO.receive();
        } catch (Exception e) {
            throw new ConnectorException("Can't connect with server");
        }
    }

    public void close() {
        try {
            this.connectorIO.close();
            this.socket.close();
        } catch (IOException e) {
            throw new ConnectorException("Can't close connection!");
        }
    }
}

