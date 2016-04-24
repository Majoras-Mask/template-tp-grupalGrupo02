package ar.fiuba.tdd.tp.factory;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;
import ar.fiuba.tdd.tp.factory.input.converter.CommandConverterFactory;
import ar.fiuba.tdd.tp.factory.input.supplier.ClientSupplierFactory;
import ar.fiuba.tdd.tp.factory.output.ClientConsumerFactory;

public class ClientFactory {
    private final ClientConsumerFactory clientConsumerFactory;
    private final ClientSupplierFactory clientSupplierFactory;

    public ClientFactory() {
        this.clientSupplierFactory = new ClientSupplierFactory();
        this.clientConsumerFactory = new ClientConsumerFactory();
    }

    public Client createStandardClient() {
        return new Client(getSupplier(), getConsumer(), getConverter());
    }

    public ClientSupplier getSupplier() {
        return this.clientSupplierFactory.createStdinSupplier();
    }

    public ClientConsumer getConsumer() {
        return this.clientConsumerFactory.createSysoutConsumer();
    }

    public RequestConverter getConverter() {
        final CommandProcessor commandProcessor = new CommandProcessor();
        return new CommandConverterFactory(commandProcessor).createConverters();
    }
}
