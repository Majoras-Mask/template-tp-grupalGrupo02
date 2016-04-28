package ar.fiuba.tdd.tp.server.io;

/**
 * Created by manuelcruz on 26/04/2016.
 */
public class ServerOutput {
    private static final String PREFIX = "Server> ";

    private static void print(String string) {System.out.print(PREFIX + string + '\n');
    }

    public static void clientConnected(Integer localPort) {
        print("Client connected on port " + localPort);
    }

    public static void clientDisconnected(Integer localPort) {
        print("Offline client on port " + localPort);
    }

    public static void welcomeMessage() {
        print("Welcome to Majora's Mask game service, open a new game typing 'load game'");
    }

    public static void newGame(Integer port) {
        print("New game opened at port " + port);
    }

    public static void noPortsAvailable() {
        print("No ports available");
    }

    public static void unknownCommand() {
        print("Unknown command, try with 'close' 'exit' or 'connect'");
    }

    public static void unopenedConnection(Integer port) {
        print("Couldn't open connection at port " + port);
    }

    public static void choosePort() {
        print("Please choose a port to close");
    }

    public static void closedPort(Integer port) {
        print("The port " + port + " is now closed");
    }

    public static void unreachedPort(Integer port) {
        print("Could not close port " + port);
    }
}
