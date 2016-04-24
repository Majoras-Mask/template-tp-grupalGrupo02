package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Connector {

    private Boolean started;
    private Socket socket;
    private ConnectorIO connectorIO;

    public Connector() {
        this.started = Boolean.FALSE;
    }

    public void init(ConnectorSettings settings) throws IOException {
        this.init(getNewSocket(settings));
    }

    public void init(Socket socket) throws IOException {
        this.started = Boolean.TRUE;
        this.socket = requireNonNull(socket);
        this.connectorIO = initIO();
    }

    private Socket getNewSocket(ConnectorSettings settings) throws IOException {
        if (Objects.isNull(settings)) {
            throw new IllegalArgumentException("Settings can't be null");
        }

        final String host = requireNonNull(settings.getHost());
        final int port = requireNonNull(settings.getPort());

        return new Socket(host, port);
    }

    private ConnectorIO initIO() throws IOException {
        try {
            return new ConnectorIO(this.socket);
        } catch (Exception e) {
            this.started = Boolean.FALSE;
            throw e;
        } finally {
            this.socket.close();
        }
    }

    public Boolean isStarted() {
        return this.started;
    }

    public void send(Request request) throws Exception {
        if (isStarted()) {
            doSend(request);
        }
        throw new IllegalStateException("The connector was not started!!");
    }

    public Response receive() throws Exception {
        if (isStarted()) {
            return this.doReceive();
        }
        throw new IllegalStateException("The connector was not started!!");
    }

    private Response doReceive() throws Exception {
        return this.connectorIO.receive();
    }

    private void doSend(Request request) throws Exception {
        this.connectorIO.send(request);
    }

    public void close() throws IOException {
        this.started = Boolean.FALSE;
        this.socket.close();
        this.connectorIO.close();
    }
}

