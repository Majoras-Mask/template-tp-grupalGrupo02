package ar.fiuba.tdd.tp;

public class ServerApi {

    private final String test1;
    private final String test2;

    public ServerApi(String test1, String test2) {
        this.test1 = test1;
        this.test2 = test2;
    }

    public String getTest1() {
        return test1;
    }

    public String getTest2() {
        return test2;
    }
}
