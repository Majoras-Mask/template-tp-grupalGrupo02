package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.connector.Connector;
import ar.fiuba.tdd.tp.input.CommandSupplier;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.output.consumer.ClientResponse;

import java.util.List;

import static ar.fiuba.tdd.tp.utils.Constants.*;

public class Client {

    private Boolean running;
    private final CommandSupplier supplier;
    private final Connector connector;
    private final List<ClientConsumer> consumers;

    public Client(CommandSupplier supplier, Connector connector, List<ClientConsumer> consumers) {
        this.supplier = supplier;
        this.connector = connector;
        this.consumers = consumers;
    }

    public void run() {
        this.running = Boolean.TRUE;
        this.doRun();
    }

    private void doRun() {
        while (this.isRunning()) {
            this.processCommand(this.supplier.getNext());
        }
    }

    private void processCommand(InputCommand command) {
        command.execute();
    }

    public void connect(ConnectorSettings connectorSettings) {
        try {
            this.connector.init(connectorSettings);
            this.callConsumers(CONNECTION_SUCCESSFUL);
        } catch (Exception e) {
            this.callConsumers(CONNECTION_EXCEPTION);
        }
    }

    public void close() {
        try {
            this.connector.close();
            this.callConsumers(CLOSE_SUCCESSFUL);
        } catch (Exception e) {
            this.callConsumers(CLOSE_EXCEPTION);
        }
    }

    public void sendRequest(Request request) {
        try {
            this.connector.send(request);
        } catch (Exception e) {
            this.callConsumers(CONNECTION_EXCEPTION);
        }
    }

    public Response getResponse() {
        Response response = null;
        try {
            response = this.connector.receive();
            this.callConsumers(new ClientResponse(response.getSomething()));
        } catch (Exception e) {
            this.callConsumers(CONNECTION_EXCEPTION);
        }
        return response;
    }

    public void callConsumers(List<ClientResponse> events) {
        events.forEach(this::callConsumers);
    }

    public void callConsumers(ClientResponse event) {
        this.consumers.forEach(consumer -> consumer.consume(event));
    }

    public void help(String gameName) {
        this.sendRequest(new Request(gameName));
        final Response response = getResponse();
        this.callConsumers(new ClientResponse(response.getSomething()));
    }

    public Boolean isRunning() {
        return running;
    }
}
