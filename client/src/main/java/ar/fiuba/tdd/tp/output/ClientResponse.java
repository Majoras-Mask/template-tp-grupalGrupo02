package ar.fiuba.tdd.tp.output;

import ar.fiuba.tdd.tp.api.Response;

public class ClientResponse {
    private final String event;

    public ClientResponse(Response response) {
        this.event = response.getSomething();
    }

    public ClientResponse(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
