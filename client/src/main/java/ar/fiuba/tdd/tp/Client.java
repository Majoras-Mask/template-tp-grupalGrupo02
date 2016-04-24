package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.input.supplier.CommandSupplier;
import ar.fiuba.tdd.tp.output.ClientResponse;
import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;

import static ar.fiuba.tdd.tp.utils.Constants.PROCESS_COMMAND_ERROR;

public class Client {
    private Boolean running;
    private final CommandSupplier supplier;
    private final ClientConsumer consumer;

    public Client(CommandSupplier supplier, ClientConsumer consumer) {
        this.supplier = supplier;
        this.consumer = consumer;
        this.running = Boolean.FALSE;
    }

    public void run() {
        this.running = Boolean.TRUE;
        this.doRun();
    }

    private void doRun() {
        while (this.isRunning()) {
            this.callConsumer(this.processCommand());
        }
    }

    private ClientResponse processCommand() {
        try {
            return this.supplier.getCommand().execute();
        } catch (CommandProcessorException e) {
            return PROCESS_COMMAND_ERROR;
        }
    }

    public void callConsumer(ClientResponse event) {
        this.consumer.consume(event);
    }

    public Boolean isRunning() {
        return running;
    }

}
