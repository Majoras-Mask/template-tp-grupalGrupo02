package ar.fiuba.tdd.tp.api;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Request request;
    private Response response;
    //private Game game;

    public Connection(ServerSocket serverSocket/*, Game game*/) {
        this.serverSocket = requireNonNull(serverSocket);
        //this.game = game;
    }

    public void closeConnection() {
        try {
            if (!isNull(clientSocket) && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getStream(Socket clientSocket) throws IOException {
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    private void speak() throws IOException, ClassNotFoundException {
        welcome();
        boolean exit = false;
        while (!exit && !isNull(request = (Request) inputStream.readObject())) {
            if (request.getSomething().equals("exit game")) {
                //TODO: send closure to client
                exit = true;
            }
            //TODO: send client request to game
            //TODO: send game response to client
        }
    }

    private void welcome() throws IOException {
        response = new Response("Welcome");
        outputStream.writeObject(response);
        outputStream.flush();
    }

    public void run() {
        try {
            while (!serverSocket.isClosed() && !isNull(clientSocket = serverSocket.accept())) {
                //TODO: inform that new connection was established
                getStream(clientSocket);
                speak();
                clientSocket.close();
                //TODO: inform that client was disconnected
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}