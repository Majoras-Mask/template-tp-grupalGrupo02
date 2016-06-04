package ar.fiuba.tdd.tp.client.connector;


import ar.fiuba.tdd.tp.client.exception.ConnectorException;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;

import java.io.IOException;
import java.net.Socket;

public class Connector {

    private final Socket socket;
    private final ConnectorIO connectorIO;

    public Connector(Socket socket, ConnectorIO connectorIO) {
        this.socket = socket;
        this.connectorIO = connectorIO;
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

    public Response receiveIfAvailable() {
        try {
            return this.connectorIO.receiveIfAvailable();
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

