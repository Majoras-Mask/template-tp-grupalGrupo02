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

public class ClientConnection extends Thread {

    private final ServerSocket serverSocket;
    private final Game game;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Request request;
    private Response response;
    private List<Socket> allClientSockets = new LinkedList<>();


    public ClientConnection(Socket clientSocket, Game game, ServerSocket serverSocket) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.serverSocket = serverSocket;
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
        welcome();
        boolean exit = false;
        while (!exit && nonNull(request = (Request) inputStream.readObject())) {
            if (request.isExit()) {
                exit = true;
                response = new Response("exit");
            } else {
                String message = request.getSomething();
                response = new Response(game.command(clientSocket.getPort(), message));
                notifyAllListeners(message);
            }
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private void welcome() throws IOException {
        response = new Response(game.getWelcomeMessage());
        outputStream.writeObject(response);
        outputStream.flush();
    }

    private void getStream(Socket clientSocket) throws IOException {
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void setListeners(List<Socket> allClientSocketss) {
        this.allClientSockets = allClientSocketss;
        this.allClientSockets.remove(clientSocket);  //Removes itself
    }

    private void notifyAllListeners(String message) throws IOException {
        for (Socket socket : allClientSockets) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(new Response(message));
            outputStream.flush();
        }
    }
}
