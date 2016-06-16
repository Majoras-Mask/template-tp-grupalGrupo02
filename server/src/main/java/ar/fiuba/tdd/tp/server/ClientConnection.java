package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import static java.util.Objects.nonNull;

public class ClientConnection extends Thread {

    private final ServerSocket serverSocket;
    private final Game game;
    private final String playerID;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Request request;
    private Response response;
    private Connection connection;


    public ClientConnection(Socket clientSocket, Game game, ServerSocket serverSocket, String playerID,
                            ObjectOutputStream outputStream, Connection connection) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.serverSocket = serverSocket;
        this.playerID = playerID;
        this.outputStream = outputStream;
        this.connection = connection;
    }

    public void run() {
        try {
            getStream(clientSocket);
            speak();
            game.leavePlayer(playerID);
            connection.removePlayer(playerID);
            clientSocket.close();
            ServerOutput.clientDisconnected(serverSocket.getLocalPort());
        } catch (ClassNotFoundException | IOException e)  {
            game.leavePlayer(playerID);
            connection.removePlayer(playerID);
            ServerOutput.threadFinished();
        }
    }

    private void printWelcomeMessage() throws IOException {
        if (playerID.isEmpty()) {
            response = new Response("No player available. You can't play :(. Try again later. Bye bye.");
            outputStream.writeObject(response);
            outputStream.flush();
            response = new Response("exit");
        } else {
            response = new Response("Welcome to the game, you are ".concat(playerID));
        }

        outputStream.writeObject(response);
        outputStream.flush();
    }

    private void speak() throws IOException, ClassNotFoundException {
        printWelcomeMessage();
        boolean exit = false;
        while (!exit && nonNull(request = (Request) inputStream.readObject())) {
            if (request.isExit()) {
                exit = true;
                response = new Response("exit");
            } else {
                String message = request.getSomething();
                response = new Response(game.executeCommand(playerID, message));
            }

            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private void getStream(Socket clientSocket) throws IOException {
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

}
