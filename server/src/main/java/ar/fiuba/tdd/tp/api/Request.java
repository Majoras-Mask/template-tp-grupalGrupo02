package ar.fiuba.tdd.tp.api;

import java.io.Serializable;

public class Request implements Serializable {

    private String something;

    public Request(String something) {
        this.something = something;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public boolean isExit() {
        return something.equals("exit game");
    }
}
