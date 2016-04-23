package ar.fiuba.tdd.tp.input.converter.client;

import ar.fiuba.tdd.tp.Client;
import ar.fiuba.tdd.tp.config.ConnectorSettings;
import ar.fiuba.tdd.tp.input.command.client.ConnectCommand;
import ar.fiuba.tdd.tp.input.converter.AbstractCommandConverter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectCommandConverter extends AbstractCommandConverter {

    private static final String BETWEEN_0_AND_255 = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IP_ADDRESS = BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255;
    private static final String PORT = "(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3})";

    public ConnectCommandConverter(Client client) {
        super(client, new ArrayList<String>() { {
                add("^connect " + IP_ADDRESS + ":" + PORT + "$");
            }
        });
    }

    protected ConnectCommand doConvert(String input) {
        return new ConnectCommand(this.client, getConnectionSettings(input.replaceFirst("connect", "")));
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
