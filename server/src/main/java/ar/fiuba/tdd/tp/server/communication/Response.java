package ar.fiuba.tdd.tp.server.communication;

import java.io.Serializable;

public class Response implements Serializable {

    private String something;

    public Response(String something) {
        this.something = something;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }
}
