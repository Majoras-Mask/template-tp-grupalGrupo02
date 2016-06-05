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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ar.fiuba.tdd.tp.client.utils.Constants.*;

public class ClientCore {

    private Boolean running;
    private Boolean connected;
    private Connector connector;
    private List<ClientListener> listeners;

    public ClientCore() {
        this.running = Boolean.FALSE;
        this.connected = Boolean.FALSE;
        this.listeners = new CopyOnWriteArrayList<>();
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

    public void connect(ConnectorSettings settings) {
        if (!this.isConnected()) {
            this.connector = createConnector(settings);
            this.connected = Boolean.TRUE;

            (new Thread(this::receiveThread)).start();
            return;
        }
        throw new ClientException(ANOTHER_OPEN_CONNECTION);
    }

    public void send(ClientRequest request) {
        if (this.isConnected()) {
            this.connector.send(new Request(request.getInput()));
            return;
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
        } catch (Exception e)  {
            throw new ConnectorException(OPEN_ERROR);
        }
    }

    private Socket createSocket(ConnectorSettings settings) throws IOException {
        final Socket socket = new Socket();
        socket.connect(new InetSocketAddress(settings.getHost(), settings.getPort()));

        return socket;
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

    private void receiveThread() {
        while (isConnected()) {
            this.notifyListeners(this.receive());
        }
    }

    private ClientResponse receive() {
        final Response response = this.connector.receive();
        if (response.isExit()) {
            this.stopConnector();
        }

        return new ClientResponse(response.getSomething());
    }

    public void addListener(ClientListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ClientListener listener) {
        this.listeners.remove(listener);
    }

    public void notifyListeners(ClientResponse response) {
        this.listeners.forEach(listener -> listener.listen(response));
    }
}
