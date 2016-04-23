package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.output.consumer.ClientEvent;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final List<ClientEvent> CONNECTION_SUCCESSFUL = new ArrayList<ClientEvent>() { {
            add(new ClientEvent("The connection was successful!"));
            add(new ClientEvent("Type what you want to do..."));
        }
    };

    public static final List<ClientEvent> CONNECTION_EXCEPTION = new ArrayList<ClientEvent>() { {
            add(new ClientEvent("Something really bad happened while connecting with the server."));
            add(new ClientEvent("I'm sorry, but you'll have to go and play somewhere else.."));
        }
    };

    public static final List<ClientEvent> CLOSE_SUCCESSFUL = new ArrayList<ClientEvent>() { {
            add(new ClientEvent("Connection with server closed!"));
        }
    };

    public static final List<ClientEvent> CLOSE_EXCEPTION = new ArrayList<ClientEvent>() { {
            add(new ClientEvent("A problem occurred while closing connection with server."));
            add(new ClientEvent("did we ever connect with him?"));
        }
    };
}
