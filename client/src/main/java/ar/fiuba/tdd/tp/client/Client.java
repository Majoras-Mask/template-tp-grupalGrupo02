package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.client.utils.Constants;

import static ar.fiuba.tdd.tp.client.utils.Constants.PROCESS_COMMAND_ERROR;

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

    public void callConsumer(ClientResponse event) {
        this.consumer.consume(event);
    }

    public Boolean isRunning() {
        return running;
    }

    public void stop(){
        this.running = Boolean.FALSE;
    }

    public void start(){
        this.running = Boolean.TRUE;
    }
}
