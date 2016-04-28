package ar.fiuba.tdd.tp.api;

/**
 * Created by manuelcruz on 26/04/2016.
 */
public class ServerOutput {
    private static final String PREFIX = "Server> ";

    public static void clientConnected(int localPort) {
        System.out.println(PREFIX + "Client connected on port " + localPort);
    }

    public static void clientDisconnected(int localPort) {
        System.out.println(PREFIX + "Offline client on port " + localPort);
    }

    public static void welcomeMessage() {
        System.out.println(PREFIX + "Welcome to Majora's Mask game service, open a new game typing 'load game'");
    }

    public static void newGame(Integer port) {
        System.out.println(PREFIX + "New game opened at port " + port);
    }

    public static void noPortsAvailable(int basePort, int endPort) {
        System.out.println(PREFIX + "No ports available from " + basePort + " to " + endPort);
    }

    public static void unknownCommand() {
        System.out.println(PREFIX + "Unknown command, try with 'close' 'exit' or 'connect'");
    }

    public static void unopenedConnection(Integer port) {
        System.out.println(PREFIX + "Couldn't open connection at port " + port);
    }
}
