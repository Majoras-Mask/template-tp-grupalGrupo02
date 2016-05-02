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

    interface GameGenerator {
        Engine create();
    }

    private ServerInput serverInput;
    private Map<Integer,Connection> connections;
    private Map<Command,Process> commands;
    private Map<String,GameGenerator> gamesGenerator;
    private static final EngineFactoryConcrete engineFactory = EngineFactoryConcrete.getInstance();
    private static final int FIRST_PORT = 8000;
    private static final int LAST_PORT = 8100;

    public Server() throws UnsupportedEncodingException {
        connections = new HashMap<>();
        serverInput = new ServerInput();
        setCommands();
        setEngines();
    }

    private void setCommands() {
        commands = new HashMap<>();
        commands.put(Command.LOAD, this::loadGame);
        commands.put(Command.EXIT, this::closeConnections);
        commands.put(Command.NONE, ServerOutput::unknownCommand);
        commands.put(Command.CLOSE, this::closeConnection);
        commands.put(Command.HELP, ServerOutput::help);
    }

    private void setEngines() {
        gamesGenerator = new HashMap<>();
        gamesGenerator.put("fetch", engineFactory::createEngineFetch);
        gamesGenerator.put("hanoi", engineFactory::createEngineHanoi);
        gamesGenerator.put("riddle", engineFactory::createEngineRiddle);
        gamesGenerator.put("open door1", engineFactory::createEngineOpenDoor);
        gamesGenerator.put("open door2", engineFactory::createEngineOpenDoorTwo);
        gamesGenerator.put("cursed object", engineFactory::createEngineCursedObject);
        gamesGenerator.put("treasure hunt", engineFactory::createEngineTreasureHunt);
    }

    public void run() {
        Command command;
        ServerOutput.welcomeMessage();
        while ((command = serverInput.readCommand()) != Command.EXIT) {
            commands.get(command).exec();
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
            ServerOutput.chooseGame();
            String gameName = serverInput.readGame();
            if (!gamesGenerator.containsKey(gameName)) {
                ServerOutput.invalidGame();
            } else {
                Connection connection;
                try {
                    connection = new Connection(new ServerSocket(port), gamesGenerator.get(gameName).create());
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
        ServerOutput.choosePort();
        Integer port = serverInput.readPort();
        if (port >= FIRST_PORT && port <= LAST_PORT && connections.containsKey(port)) {
            connections.get(port).closeConnection();
            connections.remove(port);
            ServerOutput.closedPort(port);
        } else {
            ServerOutput.unreachedPort(port);
        }

    }
}