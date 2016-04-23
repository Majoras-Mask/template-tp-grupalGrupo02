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
        IpPort ipPort;
        clientProtocol.init();
        while (!clientProtocol.connected() && (ipPort = clientProtocol.readEntry()).isValid()) {
            clientProtocol.tryConnect(ipPort);
        }
        return clientProtocol.connected();
    }

    public void runConnected() {
        while (clientProtocol.listen()) {
            clientProtocol.sendEntry();
        }
    }
}
