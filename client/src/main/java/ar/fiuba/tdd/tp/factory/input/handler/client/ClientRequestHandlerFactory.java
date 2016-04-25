package ar.fiuba.tdd.tp.factory.input.handler.client;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.ConnectRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.ExitRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.HelpRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class ClientRequestHandlerFactory {

    private final Client client;

    public ClientRequestHandlerFactory(Client client) {
        this.client = client;
    }

    public List<AbstractRequestHandler> createClientHandlers() {
        final AbstractRequestHandler connectHandler = createConnectHandler();
        final AbstractRequestHandler exitHandler = createExitHandler();
        final AbstractRequestHandler helpHandler = createHelpHandler();

        return new ArrayList<AbstractRequestHandler>() { {
                add(connectHandler);
                add(exitHandler);
                add(helpHandler);
            }
        };
    }

    public ConnectRequestHandler createConnectHandler() {
        return new ConnectRequestHandler(this.client, null);
    }

    public ExitRequestHandler createExitHandler() {
        return new ExitRequestHandler(this.client, null);
    }

    public HelpRequestHandler createHelpHandler() {
        return new HelpRequestHandler(this.client, null);
    }

}
