package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.output.ClientResponse;

public interface ClientListener {
    void listen(ClientResponse response);
}
