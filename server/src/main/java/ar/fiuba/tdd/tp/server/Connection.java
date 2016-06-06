package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.server.communication.Response;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread implements Sender {
    private final ServerSocket serverSocket;
    private final Game game;
    private HashMap<String, Socket> clientSockets = new HashMap<>();
    private HashMap<String, ObjectOutputStream> clientOutputs = new HashMap<>();

    public Connection(ServerSocket serverSocket, Game game) {
        this.serverSocket = requireNonNull(serverSocket);
        this.game = game;
        this.game.setSender(this);
        this.game.startLoop();
    }

    public void closeConnection() {
        try {
            for (Map.Entry<String,Socket> entry : clientSockets.entrySet()) {
                Socket clientSocket = entry.getValue();
                if (nonNull(clientSocket) && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                ServerOutput.clientConnected(serverSocket.getLocalPort());
                String playerID = game.getPlayerIDAvailable();
                ObjectOutputStream clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                ClientConnection client = new ClientConnection(clientSocket, game, serverSocket, playerID, clientOutput);
                clientSockets.put(playerID, clientSocket);
                clientOutputs.put(playerID, clientOutput);
                client.start();
            }
        } catch (IOException e) {
            ServerOutput.threadFinished();
        }
    }

    @Override
    public void sendAll(String message) {
        for (String playerID : clientSockets.keySet()) {
            send(playerID, message);
        }
    }

    @Override
    public void send(String playerID, String message) {
        try {
            if (clientSockets.containsKey(playerID) && !clientSockets.get(playerID).isClosed()) {
                ObjectOutputStream outputStream = clientOutputs.get(playerID);
                Response response = new Response(message);
                outputStream.writeObject(response);
                outputStream.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}