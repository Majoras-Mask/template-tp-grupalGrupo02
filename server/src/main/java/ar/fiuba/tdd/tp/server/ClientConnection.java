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


    public ClientConnection(Socket clientSocket, Game game, ServerSocket serverSocket, String playerID, ObjectOutputStream outputStream) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.serverSocket = serverSocket;
        this.playerID = playerID;
        this.outputStream = outputStream;
    }

    public void run() {
        try {
            getStream(clientSocket);
            speak();
            clientSocket.close();
            ServerOutput.clientDisconnected(serverSocket.getLocalPort());
        } catch (ClassNotFoundException | IOException e)  {
            ServerOutput.threadFinished();
        }
    }

    private void speak() throws IOException, ClassNotFoundException {
        response = new Response("bienvenidos");
        outputStream.writeObject(response);
        outputStream.flush();
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
