package ar.fiuba.tdd.tp.output.consumer;

import ar.fiuba.tdd.tp.output.ClientResponse;

/**
 * Consume messages from the server, and does what he wants with it.
 */
public interface ClientConsumer {
    void consume(ClientResponse event);
}
