package ar.fiuba.tdd.tp.api;

import ar.fiuba.tdd.tp.motor.games.EngineFactoryConcrete;
import ar.fiuba.tdd.tp.server.utils.Command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    interface Process {
        void exec();
    }

    private ServerInput serverInput;
    private Map<Integer,Connection> connections;
    private Map<Command,Process> commands;
    private static final int FIRST_PORT = 8000;
    private static final int LAST_PORT = 8100;

    public Server() throws UnsupportedEncodingException {
        connections = new HashMap<>();
        serverInput = new ServerInput();
        commands = new HashMap<>();
        commands.put(Command.LOAD, this::loadGame);
        commands.put(Command.EXIT, this::closeConnections);
        commands.put(Command.NONE, ServerOutput::unknownCommand);
        commands.put(Command.CLOSE, this::closeConnection);
    }

    public void run() {
        Command entry;
        serverInput.init();
        while ((entry = serverInput.readEntry()) != Command.EXIT) {
            commands.get(entry).exec();
        }
        commands.get(Command.EXIT).exec();
    }

    private Integer getFreePort() {
        Integer port = FIRST_PORT;
        while (connections.containsKey(port) && port < LAST_PORT) {
            port++;
        }
        return port;
    }

    private void loadGame() {
        Integer port = getFreePort();
        if (port == LAST_PORT) {
            ServerOutput.noPortsAvailable();
            return;
        }
        Connection connection;
        try {
            connection = new Connection(new ServerSocket(port), EngineFactoryConcrete.getInstance().createEngineHanoi());
            connection.start();
            connections.put(port, connection);
            ServerOutput.newGame(port);
        } catch (IOException e) {
            ServerOutput.unopenedConnection(port);
        }
    }

    private void closeConnections() {
        connections.forEach((port,connection)->connection.closeConnection());
    }

    private void closeConnection() {
        Integer port = serverInput.getPort();
        if (port >= FIRST_PORT && port <= LAST_PORT && connections.containsKey(port)) {
            connections.get(port).closeConnection();
        }
        connections.remove(port);
        ServerOutput.closedPort(port);
    }
}