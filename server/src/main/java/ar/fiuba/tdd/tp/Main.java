package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.api.Server;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
            server.run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
