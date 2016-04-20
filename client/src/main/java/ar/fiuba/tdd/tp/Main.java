package ar.fiuba.tdd.tp;

public class Main {
    public static void main(String[] args) {
        ServerApi serverApi = getServerApi();
        System.out.println("This is just a client!");
        System.out.println(serverApi.getTest1());
        System.out.println(serverApi.getTest2());
    }

    public static ServerApi getServerApi() {
        return new ServerApi("Using server","API");
    }
}
