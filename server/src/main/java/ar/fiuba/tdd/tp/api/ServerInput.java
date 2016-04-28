package ar.fiuba.tdd.tp.api;

import ar.fiuba.tdd.tp.server.utils.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ServerInput {
    private BufferedReader inputBuffer;

    public ServerInput() throws UnsupportedEncodingException {
        inputBuffer = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    }

    public void init() {
        ServerOutput.welcomeMessage();
    }

    public Command readEntry() {
        String input;
        try {
            input = inputBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return Command.NONE;
        }
        if (input == null) {
            return Command.NONE;
        }
        return readInput(input);
    }

    public Command readInput(String input) {
        //Matcher matcher = pattern.matcher(input);
        if (input.equals("load game")) {
            return Command.LOAD;
        }
        if (input.equals("exit")) {
            return Command.EXIT;
        }
        return Command.NONE;
    }

}