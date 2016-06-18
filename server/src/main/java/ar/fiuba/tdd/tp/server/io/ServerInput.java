package ar.fiuba.tdd.tp.server.io;

import ar.fiuba.tdd.tp.server.utils.Command;
import ar.fiuba.tdd.tp.server.utils.PortPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerInput {
    private BufferedReader inputBuffer;
    private static Map<String, Command> commands;
    private String inputTrail;

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

    private String readString() {
        String input;
        try {
            input = inputBuffer.readLine();
        } catch (IOException e) {
            input = "";
        }
        return input;
    }

    public Command readCommand() {
        String input = readString();
        for (String command : commands.keySet()) {
            if (input.startsWith(command)) {
                this.inputTrail = input.replace(command + " ", "");
                return commands.get(command);
            }
        }
/*        if (commands.containsKey(input)) {
            return commands.get(input);
        }*/
        return Command.NONE;
    }

    public Integer readPort() {
        Integer port = 0;
        String input = readString();
        Pattern portPattern = PortPattern.getPattern();
        Matcher matcher = portPattern.matcher(input);
        if (matcher.find()) {
            port = Integer.parseInt(input);
        }
        return port;
    }

    public String readGame() {
        return readString();
    }

    public String getInputTrail() {
        return inputTrail;
    }
}