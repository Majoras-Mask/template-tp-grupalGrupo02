package ar.fiuba.tdd.tp;

/**
 * Created by kevin on 15/06/16.
 */
public class SenderNull implements Sender {
    @Override
    public void sendAll(String message) {
        return;
    }

    @Override
    public void send(String playerID, String message) {
        return;
    }
}
