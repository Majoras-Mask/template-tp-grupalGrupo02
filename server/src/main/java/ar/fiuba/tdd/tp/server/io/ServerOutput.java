package ar.fiuba.tdd.tp.server.io;

public class ServerOutput {
    private static final String PREFIX = "Server> ";
    private static final String GAMES = "'fetch', 'hanoi', 'riddle', 'open door1', 'open door2', 'cursed object' and 'treasure hunt'";
    private static final String COMMANDS = "'load game', 'close port', 'exit' and 'help'";

    private static void print(String string) {
        System.out.print(PREFIX + string + '\n');
    }

    public static void clientConnected(Integer localPort) {
        print("Client connected on port " + localPort);
    }

    public static void clientDisconnected(Integer localPort) {
        print("Offline client on port " + localPort);
    }

    public static void welcomeMessage() {
        print("Welcome to Majora's Mask game service, type 'help' to know the commands");
    }

    public static void newGame(Integer port) {
        print("New game opened at port " + port);
    }

    public static void noPortsAvailable() {
        print("No ports available");
    }

    public static void unknownCommand() {
        print("Unknown command, try with 'help'");
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

    public static void help() {
        print("The available commands are " + COMMANDS);
    }

    public static void unreachedPort(Integer port) {
        print("Could not close port " + port);
    }

    public static void threadFinished() {
        print("Thread finished");
    }

    public static void invalidGame() {
        print("That's not a valid game, choose between " + GAMES);
    }

    public static void chooseGame() {
        print("Please provide a path to a game Jar.");
    }

    public static void notEnoughPrivileges() {
        print("You don't have the sufficient privileges to access that file.");
    }

    public static void instantiationException() {
        print("We got an instantiationException error.");
    }

    public static void noClassFound() {
        print("No class found with that name. Which name? who knows!");
    }
}