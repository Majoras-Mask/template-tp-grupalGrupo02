package ar.fiuba.tdd.tp;

public class Main {
    public static void main(String[] args) {
        ServerApi serverApi = getServerApi();

        //Uncomment this method call to test checkstyle
//        tstMethod("1","1","1","1","1","1");

        System.out.println("This is just a client!");
        System.out.println(serverApi.getTest1());
        System.out.println(serverApi.getTest2());
    }


//    private static String tstMethod(String param1, String param2, String param3, String param4, String param5, String param6) {
//        if (param1.equals(param2))
//            return param2;
//
//        if (param1.equals(param4))
//            return param4;
//
//        if (param1.equals(param3))
//            return param2;
//
//        if (param3.equals(param4))
//            return "TEST";
//
//        if (param5.equals(param1))
//            return "Esperemos que el checkstyle falle...";
//
//        return "";
//    }

    public static ServerApi getServerApi() {
        return new ServerApi("Using server","API");
    }
}
