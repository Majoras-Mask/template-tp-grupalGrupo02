package ar.fiuba.tdd.tp.api;

/**
 * Created by manuelcruz on 26/04/2016.
 */
public class ServerIO {
    public static void clientConnected(int localPort) {
        System.out.println("Client connected on port " + localPort);
    }

    public static void clientDisconnected(int localPort) {
        System.out.println("Offline client on port " + localPort);
    }
}
