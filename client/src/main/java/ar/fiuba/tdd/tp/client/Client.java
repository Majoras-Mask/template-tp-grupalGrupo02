package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ClientException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.factory.input.handler.CommandConverterFactory;

import java.util.Optional;

import static ar.fiuba.tdd.tp.client.utils.Constants.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A Client that handles the communication for a Game. Consumes requests from a {@link ClientSupplier}.
 * Those requests are handled by one {@link RequestHandler} and then this result is communicated
 * to the {@link ClientConsumer}.
 */
public class Client {

    private Boolean running;
    private RequestHandler handler;
    private Connector connector;
    private final ClientSupplier supplier;
    private final ClientConsumer consumer;

    public Client(ClientSupplier supplier, ClientConsumer consumer) {
        this.supplier = supplier;
        this.consumer = consumer;
        this.handler = newHandler();
        this.running = Boolean.FALSE;
    }

    public void run() {
        this.start();
        this.callConsumer(WELCOME);
        this.doRun();
    }

    private void doRun() {
        while (this.isRunning()) {
            this.processCommand();
        }
    }

    private void processCommand() {
        Optional<ClientResponse> response = this.processRequest(this.supplier.getRequest());
        if (response.isPresent()) {
            callConsumer(response.get());
        }
    }

    private Optional<ClientResponse> processRequest(ClientRequest request) {
        try {
            return this.handler.handle(request);
        } catch (ClientException e) {
            return Optional.of(new ClientResponse(PROCESS_COMMAND_ERROR + e.getMessage()));
        }
    }

    private void callConsumer(ClientResponse event) {
        this.consumer.consume(event);
    }

    public ClientResponse connect(ConnectorSettings settings) {
        if (isNull(this.connector)) {
            this.connector = new Connector(settings);
            return CONNECTION_SUCCESSFUL;
        }
        throw new ClientException(ANOTHER_OPEN_CONNECTION);
    }

    public ClientResponse sendAndReceive(ClientRequest request) {
        if (nonNull(this.connector)) {
            this.connector.send(new Request(request.getInput()));
            return new ClientResponse(this.connector.receive().getSomething());
        }
        throw new ClientException(OPEN_CONNECTION_FIRST);
    }

    private RequestHandler newHandler() {
        return new CommandConverterFactory(this).createConverters();
    }

    public void stop() {
        this.running = Boolean.FALSE;
        this.stopConnector();
    }

    private void stopConnector() {
        if (nonNull(this.connector)) {
            this.connector.close();
            this.connector = null;
        }
    }

    public void start() {
        this.running = Boolean.TRUE;
    }

    public Boolean isRunning() {
        return running;
    }

}
