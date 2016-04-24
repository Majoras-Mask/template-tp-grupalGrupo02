package ar.fiuba.tdd.tp.client.input.command.converter.client;

import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.input.command.client.ConnectCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectCommandConverter extends AbstractCommandConverter {

    private static final String BETWEEN_0_AND_255 = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String CONNECT = "connect";
    private static final String IP_ADDRESS = BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255;
    private static final String PORT = "(([0-9])|([1-9][0-9])|([1-9][0-9]{2})|([1-9][0-9]{3})|([1-5][0-9]{4})|"
            + "(6[0-4][0-9]{3})|(65[0-4][0-9]{2})|(655[0-2][0-9])|(6553[0-5]))";

    public ConnectCommandConverter(CommandProcessor commandProcessor, RequestConverter nextConverter) {
        super(commandProcessor, new ArrayList<String>() { {
                add("^" + CONNECT + " " + IP_ADDRESS + ":" + PORT + "$");
            }
        }, nextConverter);
    }

    protected ConnectCommand doConvert(String input) {
        return new ConnectCommand(this.commandProcessor, getConnectionSettings(input.replaceFirst(CONNECT, "")));
    }

    private ConnectorSettings getConnectionSettings(String input) {
        return new ConnectorSettings(getHost(input), getPort(input.replaceFirst(IP_ADDRESS, "")));
    }

    private String getHost(String input) {
        Pattern pattern = Pattern.compile(IP_ADDRESS);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new IllegalStateException("Unable to parse host from " + input);
    }

    private Integer getPort(String input) {
        Pattern pattern = Pattern.compile(PORT);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(0));
        }
        throw new IllegalStateException("Unable to parse port from " + input);
    }
}
