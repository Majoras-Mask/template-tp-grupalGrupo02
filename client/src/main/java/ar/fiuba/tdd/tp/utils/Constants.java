package ar.fiuba.tdd.tp.utils;

import ar.fiuba.tdd.tp.output.consumer.ClientResponse;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final List<ClientResponse> CONNECTION_SUCCESSFUL = new ArrayList<ClientResponse>() { {
            add(new ClientResponse("The connection was successful!"));
            add(new ClientResponse("Type what you want to do..."));
        }
    };

    public static final List<ClientResponse> CONNECTION_EXCEPTION = new ArrayList<ClientResponse>() { {
            add(new ClientResponse("Something really bad happened while connecting with the server."));
            add(new ClientResponse("I'm sorry, but you'll have to go and play somewhere else.."));
        }
    };

    public static final List<ClientResponse> CLOSE_SUCCESSFUL = new ArrayList<ClientResponse>() { {
            add(new ClientResponse("Connection with server closed!"));
        }
    };

    public static final List<ClientResponse> CLOSE_EXCEPTION = new ArrayList<ClientResponse>() { {
            add(new ClientResponse("A problem occurred while closing connection with server."));
            add(new ClientResponse("did we ever connect with him?"));
        }
    };
}
