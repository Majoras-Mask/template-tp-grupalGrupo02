package ar.fiuba.tdd.tp.factory;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.CommandConverterFactory;
import ar.fiuba.tdd.tp.factory.input.supplier.CommandSupplierFactory;
import ar.fiuba.tdd.tp.factory.output.ClientConsumerFactory;
import ar.fiuba.tdd.tp.input.supplier.CommandSupplier;
import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;

public class ClientFactory {
    private final ClientConsumerFactory clientConsumerFactory;

    public ClientFactory() {
        this.clientConsumerFactory = new ClientConsumerFactory();
    }

    public Client createStandardClient() {
        return new Client(getSuppliersForClient(new CommandProcessor()), getConsumer());
    }

    private CommandSupplier getSuppliersForClient(CommandProcessor commandProcessor) {
        final CommandConverterFactory commandConverterFactory = new CommandConverterFactory(commandProcessor);
        final CommandSupplierFactory commandSupplierFactory = new CommandSupplierFactory(commandConverterFactory);
        return commandSupplierFactory.createStdinSupplier();
    }

    public ClientConsumer getConsumer() {
        return this.clientConsumerFactory.createSysoutConsumer();
    }
}
