package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.client.utils.Constants;

import static ar.fiuba.tdd.tp.client.utils.Constants.PROCESS_COMMAND_ERROR;

/**
 * A Client that handles the communication for a Game. Consumes request from a {@link ClientSupplier}.
 * Those requests are converted into a {@link Command} and then this command is executed.
 * The result is communicated to the {@link ClientConsumer}.
 */
public class Client {
    private Boolean running;
    private final ClientSupplier supplier;
    private final ClientConsumer consumer;
    private final RequestConverter converter;

    public Client(ClientSupplier supplier, ClientConsumer consumer, RequestConverter converter) {
        this.supplier = supplier;
        this.consumer = consumer;
        this.converter = converter;
        this.running = Boolean.FALSE;
    }

    public void run() {
        this.start();
        this.callConsumer(Constants.WELCOME);
        this.doRun();
    }

    private void doRun() {
        while (this.isRunning()) {
            this.callConsumer(this.processCommand());
        }
    }

    private ClientResponse processCommand() {
        try {
            final ClientRequest request = this.supplier.getRequest();
            final Command command = this.converter.convert(request);

            return command.execute();
        } catch (Exception e) {
            return new ClientResponse(PROCESS_COMMAND_ERROR + e);
        }
    }

    private void callConsumer(ClientResponse event) {
        this.consumer.consume(event);
    }

    public Boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = Boolean.FALSE;
    }

    public void start() {
        this.running = Boolean.TRUE;
    }
}
