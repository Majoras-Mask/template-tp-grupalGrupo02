package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread {
    private final ServerSocket serverSocket;
    private final Game game;
    private Socket clientSocket;
    private HashMap<String, Socket> clientSockets = new HashMap<>();

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
                String playerID = game.getPlayerIDAvailable();
                ClientConnection client = new ClientConnection(clientSocket, game, serverSocket,playerID);
                clientSockets.put(playerID, clientSocket);
                client.start();
            }
        } catch (IOException e) {
            ServerOutput.threadFinished();
        }
    }

}