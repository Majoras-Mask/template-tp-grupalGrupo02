package ar.fiuba.tdd.tp.server.io;

import ar.fiuba.tdd.tp.server.utils.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerInput {
    private BufferedReader inputBuffer;
    private static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("load game", Command.LOAD);
        commands.put("close port", Command.CLOSE);
        commands.put("exit", Command.EXIT);
        commands.put("help", Command.HELP);
    }

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
        if (commands.containsKey(input)) {
            return commands.get(input);
        }
        return Command.NONE;
    }

    public Integer getPort() {
        ServerOutput.choosePort();
        String splitPattern1 = "((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})";
        String splitPattern2 = "([1-5][0-9]{4})|([1-9][0-9]{3})|([1-9][0-9]{2})|([1-9][0-9])|([0-9]))";
        Pattern pattern = Pattern.compile(splitPattern1 + "|" + splitPattern2);
        String input;
        Integer port = 0;
        try {
            input = inputBuffer.readLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                port = Integer.parseInt(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }

    public String readGame() {
        ServerOutput.chooseGame();
        String input = "";
        try {
            input = inputBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return input;
        }
        return input;
    }
}