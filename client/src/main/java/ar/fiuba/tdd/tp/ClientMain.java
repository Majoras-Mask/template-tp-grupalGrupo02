package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.factory.ClientFactory;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new ClientFactory().createStandardClient();
        client.run();
    }
}
