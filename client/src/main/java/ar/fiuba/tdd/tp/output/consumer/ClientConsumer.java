package ar.fiuba.tdd.tp.output.consumer;

/**
 * Consume messages from the server, and does what he wants with it.
 */
public interface ClientConsumer {
    void consume(ClientResponse event);
}
