package ar.fiuba.tdd.tp.factory.input.handler.client;

import ar.fiuba.tdd.tp.client.ClientCore;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.ConnectRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.ExitRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.client.HelpRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class ClientRequestHandlerFactory {

    private final ClientCore core;

    public ClientRequestHandlerFactory(ClientCore core) {
        this.core = core;
    }

    public List<RequestHandler> createClientHandlers() {
        final RequestHandler connectHandler = createConnectHandler();
        final RequestHandler exitHandler = createExitHandler();
        final RequestHandler helpHandler = createHelpHandler();

        return new ArrayList<RequestHandler>() { {
                add(connectHandler);
                add(exitHandler);
                add(helpHandler);
            }
        };
    }

    public ConnectRequestHandler createConnectHandler() {
        return new ConnectRequestHandler(this.core);
    }

    public ExitRequestHandler createExitHandler() {
        return new ExitRequestHandler(this.core);
    }

    public HelpRequestHandler createHelpHandler() {
        return new HelpRequestHandler(this.core);
    }

}
