package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.server.communication.Response;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Exchanger;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread implements Sender {
    private final ServerSocket serverSocket;
    private final Game game;
    private HashMap<String, ClientConnection> clientConnections = new HashMap<>();

    public Connection(ServerSocket serverSocket, Game game) {
        this.serverSocket = requireNonNull(serverSocket);
        this.game = game;
        this.game.setSender(this);
        this.game.startLoop();
    }

    public void closeConnection() {
        try {
            for (ClientConnection clientConnection : clientConnections.values()) {
                clientConnection.close();
            }
            game.setGameState(GameState.Lost);
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePlayer(String playerID) {
        if (clientConnections.containsKey(playerID)) {
            clientConnections.remove(playerID);
        }
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                ServerOutput.clientConnected(serverSocket.getLocalPort());
                String playerID = game.getPlayerIDAvailable();
                ObjectOutputStream clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                ClientConnection client = new ClientConnection(clientSocket, game, serverSocket, playerID, clientOutput, this);
                clientConnections.put(playerID, client);
                client.start();
            }
        } catch (IOException e) {
            ServerOutput.threadFinished();
        }
    }

    @Override
    public void sendAll(String message) {
        for (String playerID : clientConnections.keySet()) {
            send(playerID, message);
        }
    }

    @Override
    public void send(String playerID, String message) {
        try {
            if (clientConnections.containsKey(playerID) && !clientConnections.get(playerID).getClientSocket().isClosed()) {
                ObjectOutputStream outputStream = clientConnections.get(playerID).getObjectOutputStream();
                Response response = new Response(message);
                outputStream.writeObject(response);
                outputStream.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}