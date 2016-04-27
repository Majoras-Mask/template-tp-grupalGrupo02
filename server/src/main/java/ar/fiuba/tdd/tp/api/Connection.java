package ar.fiuba.tdd.tp.api;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Connection extends Thread {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    //private Game game;

    public Connection(ServerSocket serverSocket/*, Game game*/) {
        this.serverSocket = serverSocket;
        //this.game = game;
    }

    public void closeConnection() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
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

    public void run() {
        Request inputLine;
        try {
            while (!serverSocket.isClosed() && (clientSocket = serverSocket.accept()) != null) {
                System.out.println("socket connected");
                getStream(clientSocket);
                Response res = new Response("Hello there?");
                outputStream.writeObject(res);
                outputStream.flush();

                while ((inputLine = (Request) inputStream.readObject()) != null) {
                    //intercept message
                    //send message to game
                    //send message to client
                    System.out.println(inputLine.getSomething());
                }
                System.out.println("closing clientSocket");
                clientSocket.close();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}