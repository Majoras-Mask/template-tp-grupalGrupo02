package ar.fiuba.tdd.tp;

import java.io.UnsupportedEncodingException;

/**
 * Created by manuelcruz on 21/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
        boolean successfulConnection = client.runDisconnected();
        if(successfulConnection) {
            client.runConnected();
        }
    }
}
