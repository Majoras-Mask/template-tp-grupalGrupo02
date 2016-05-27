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

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread {
    private final ServerSocket serverSocket;
    private final Game game;
    private Socket clientSocket;

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

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                ServerOutput.clientConnected(serverSocket.getLocalPort());
                ClientConnection client = new ClientConnection(clientSocket, game, serverSocket);
                client.start();
            }
        } catch (IOException e) {
            ServerOutput.threadFinished();
        }
    }
}