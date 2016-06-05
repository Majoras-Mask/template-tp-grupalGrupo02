package ar.fiuba.tdd.tp;

/**
 * Created by kevin on 05/06/16.
 */
public interface Sender {
    void sendAll(String message);

    void send(String playerID, String message);
}
