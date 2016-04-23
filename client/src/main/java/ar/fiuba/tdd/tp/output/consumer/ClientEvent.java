package ar.fiuba.tdd.tp.output.consumer;

import ar.fiuba.tdd.tp.api.Response;

public class ClientEvent {
    private final String event;

    public ClientEvent(Response response) {
        this.event = response.getSomething();
    }

    public ClientEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
