package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread {
    private final ServerSocket serverSocket;
    private final Game game;
    private Socket clientSocket;
    private List<Socket> allClientSockets = new LinkedList<>();
    private List<ClientConnection> allClientConnections = new LinkedList<>();

    public Connection(ServerSocket serverSocket, Game game) {
        this.serverSocket = requireNonNull(serverSocket);
        this.game = game;
    }

    public void closeConnection() {
        try {
            if (nonNull(clientSocket) && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addClientSocket(Socket clientSocket) {
        allClientSockets.add(clientSocket);
    }

    private void addClientConnection(ClientConnection clientConnection) {
        allClientConnections.add(clientConnection);
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                addClientSocket(clientSocket);
                ServerOutput.clientConnected(serverSocket.getLocalPort());
                ClientConnection client = new ClientConnection(clientSocket, game, serverSocket);
                addClientConnection(client);
                game.joinPlayer(clientSocket.getPort());
                makeClientsKnowEachOther();
                client.start();
            }
        } catch (IOException e) {
            ServerOutput.threadFinished();
        }
    }

    private void makeClientsKnowEachOther() {
        for (ClientConnection clientConnection : allClientConnections) {
            clientConnection.setListeners(allClientSockets);
        }
    }
}