package ar.fiuba.tdd.tp.api;

import ar.fiuba.tdd.tp.api.Connection;
import ar.fiuba.tdd.tp.api.ServerProtocol;

import java.io.*;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;


public class Server {
    private ServerProtocol serverProtocol;
    private Map<Integer,Connection> connections;
    private static final int BASEPORT = 8000;

    public Server() throws UnsupportedEncodingException {
        connections = new HashMap<>();
        serverProtocol = new ServerProtocol();
    }

    public void run() {
        int entry;
        serverProtocol.init();
        while ((entry = serverProtocol.readEntry()) != 0) {
            System.out.println(entry);
            if (entry == 1) {
                serverProtocol.close();
            }
            if (entry == 2) {
                try {
                    loadGame();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("tu madre");
                }
            }
        }
        closeConnections();
    }

    private int getFreePort() {
        Integer port = BASEPORT;
        while (connections.containsKey(port)) {
            port++;
        }
        return port;
    }

    private void loadGame() throws IOException {
        Integer port = getFreePort();
        Connection connection = new Connection(new ServerSocket(port));
        connection.start();
        connections.put(port, connection);
    }

    private void closeConnections() {
        connections.forEach((port,connection)->connection.closeConnection());
    }
}