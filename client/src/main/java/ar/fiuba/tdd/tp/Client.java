package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.config.ConnectorSettings;
import ar.fiuba.tdd.tp.connector.Connector;
import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.converter.InputConverter;
import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.output.consumer.ClientEvent;

import java.util.List;

import static ar.fiuba.tdd.tp.Constants.*;

public class Client {

    private final InputConverter converter;
    private final Connector connector;
    private final List<ClientConsumer> consumers;

    public Client(InputConverter converter, Connector connector, List<ClientConsumer> consumers) {
        this.converter = converter;
        this.connector = connector;
        this.consumers = consumers;
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

    private void processCommand(InputCommand command) {
        command.execute();
    }

    public void send(String input) {
        this.processCommand(this.converter.convert(input));
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
            this.callConsumers(new ClientEvent(response.getSomething()));
        } catch (Exception e) {
            this.callConsumers(CONNECTION_EXCEPTION);
        }
        return response;
    }

    public void callConsumers(List<ClientEvent> events) {
        events.forEach(this::callConsumers);
    }

    public void callConsumers(ClientEvent event) {
        this.consumers.forEach(consumer -> consumer.consume(event));
    }

    public void help(String gameName) {
        this.sendRequest(new Request(gameName));
        final Response response = getResponse();
        this.callConsumers(new ClientEvent(response.getSomething()));
    }

}
