package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.motor.games.Engine;
import ar.fiuba.tdd.tp.motor.games.EngineFactoryConcrete;
import ar.fiuba.tdd.tp.server.io.ServerInput;
import ar.fiuba.tdd.tp.server.io.ServerOutput;
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

    interface EngineGetter {
        Engine create();
    }

    private ServerInput serverInput;
    private Map<Integer,Connection> connections;
    private Map<Command,Process> commands;
    private Map<String,EngineGetter> gamesCreator;
    private static final EngineFactoryConcrete engineFactory = EngineFactoryConcrete.getInstance();
    private static final int FIRST_PORT = 8000;
    private static final int LAST_PORT = 8100;

    public Server() throws UnsupportedEncodingException {
        connections = new HashMap<>();
        serverInput = new ServerInput();
        setCommands();
        setGames();
    }

    private void setCommands() {
        commands = new HashMap<>();
        commands.put(Command.LOAD, this::loadGame);
        commands.put(Command.EXIT, this::closeConnections);
        commands.put(Command.NONE, ServerOutput::unknownCommand);
        commands.put(Command.CLOSE, this::closeConnection);
        commands.put(Command.HELP, ServerOutput::help);
    }

    private void setGames() {
        gamesCreator = new HashMap<>();
        gamesCreator.put("fetch", () -> engineFactory.createEngineFetch());
        gamesCreator.put("hanoi", () -> engineFactory.createEngineHanoi());
        gamesCreator.put("riddle", () -> engineFactory.createEngineRiddle());
        gamesCreator.put("open door1", () -> engineFactory.createEngineOpenDoor());
        gamesCreator.put("open door2", () -> engineFactory.createEngineOpenDoorTwo());
        gamesCreator.put("cursed object", () -> engineFactory.createEngineCursedObject());
        gamesCreator.put("treasure hunt", () -> engineFactory.createEngineTreasureHunt());
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
        } else {
            String gameName = serverInput.readGame();
            if (!gamesCreator.containsKey(gameName)) {
                ServerOutput.unvalidGame();
            } else {
                Connection connection;
                try {
                    connection = new Connection(new ServerSocket(port), gamesCreator.get(gameName).create());
                    connection.start();
                    connections.put(port, connection);
                    ServerOutput.newGame(port);
                } catch (IOException e) {
                    ServerOutput.unopenedConnection(port);
                }
            }
        }
    }

    private void closeConnections() {
        connections.forEach((port,connection)->connection.closeConnection());
    }

    private void closeConnection() {
        Integer port = serverInput.getPort();
        if (port >= FIRST_PORT && port <= LAST_PORT && connections.containsKey(port)) {
            connections.get(port).closeConnection();
            connections.remove(port);
            ServerOutput.closedPort(port);
        } else {
            ServerOutput.unreachedPort(port);
        }

    }
}