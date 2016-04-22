package ar.fiuba.tdd.tp;

import java.io.UnsupportedEncodingException;

/**
 * Created by manuelcruz on 19/04/2016.
 */
public class Client {
    private ClientProtocol clientProtocol;

    public Client() throws UnsupportedEncodingException {
        clientProtocol = new ClientProtocol();
    }

    public boolean runDisconnected() {
        int port = 0;
        clientProtocol.init();
        while (!clientProtocol.connected() && (port = clientProtocol.readEntry()) != 0) {
            if (port > 0) {
                clientProtocol.tryConnect(port);
            }
        }
        return clientProtocol.connected();
    }

    public void runConnected() {
        while (clientProtocol.listen()) {
            clientProtocol.sendEntry();
        }
    }
}
