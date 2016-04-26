package ar.fiuba.tdd.tp.api;

import ar.fiuba.tdd.tp.api.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

public class ServerProtocol {
    private BufferedReader inputBuffer;

    public ServerProtocol() throws UnsupportedEncodingException {
        inputBuffer = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    }

    public void init() {
        System.out.println("Server> Welcome to Majora's Mask game service, open a new game typing 'load game [game name]'");
    }

    public int readEntry() {
        String input;
        try {
            input = inputBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        if (input == null) {
            return 0;
        }
        return readInput(input);
    }

    public int readInput(String input) {
        //Matcher matcher = pattern.matcher(input);
        if (input.equals("load game")) {
            return 2;
        }
        return 0;
    }

    public void close() {
    }

    public void load() {
    }
}