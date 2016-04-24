package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.factory.ClientFactory;

public class Main {
    public static void main(String[] args) {
        Client client = new ClientFactory().createStandardClient();
        client.run();
    }
}
