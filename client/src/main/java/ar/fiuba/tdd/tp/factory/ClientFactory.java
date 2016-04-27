package ar.fiuba.tdd.tp.factory;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandlerResolver;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.factory.input.handler.RequestHandlerFactory;
import ar.fiuba.tdd.tp.factory.input.supplier.ClientSupplierFactory;
import ar.fiuba.tdd.tp.factory.output.ClientConsumerFactory;

import java.util.List;

public class ClientFactory {
    private final ClientConsumerFactory clientConsumerFactory;
    private final ClientSupplierFactory clientSupplierFactory;

    public ClientFactory() {
        this.clientSupplierFactory = new ClientSupplierFactory();
        this.clientConsumerFactory = new ClientConsumerFactory();
    }

    public Client createStandardClient() {
        final ClientCore core = new ClientCore();
        return new Client(core, getSupplier(), getConsumer(), getHandlerResolver(core));
    }

    public ClientSupplier getSupplier() {
        return this.clientSupplierFactory.createStdinSupplier();
    }

    public ClientConsumer getConsumer() {
        return this.clientConsumerFactory.createSysoutConsumer();
    }

    public RequestHandlerResolver getHandlerResolver(ClientCore core) {
        List<RequestHandler> handlers = new RequestHandlerFactory(core).createHandlers();
        return new RequestHandlerResolver(handlers);
    }
}
