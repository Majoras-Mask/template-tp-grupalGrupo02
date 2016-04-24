package ar.fiuba.tdd.tp.client.output.consumer;

import ar.fiuba.tdd.tp.client.output.ClientResponse;

/**
 * Consume messages from the server, and does what he wants with it.
 */
public interface ClientConsumer {
    void consume(ClientResponse response);
}
