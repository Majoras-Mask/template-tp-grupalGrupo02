package ar.fiuba.tdd.tp.client.input.handler.client;

import ar.fiuba.tdd.tp.client.Client;
import ar.fiuba.tdd.tp.client.connector.config.ConnectorSettings;
import ar.fiuba.tdd.tp.client.exception.ConverterException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.handler.AbstractRequestHandler;
import ar.fiuba.tdd.tp.client.input.handler.RequestHandler;
import ar.fiuba.tdd.tp.client.output.ClientResponse;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectRequestHandler extends AbstractRequestHandler {

    private static final String CONNECT = "connect";
    private static final String BETWEEN_0_AND_255 = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IP_ADDRESS = BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255 + "\\."
            + BETWEEN_0_AND_255;
    private static final String PORT = "((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})"
            + "|([1-9][0-9]{3})|([1-9][0-9]{2})|([1-9][0-9])|([0-9]))";

    public ConnectRequestHandler(Client client, RequestHandler nextConverter) {
        super(client, new ArrayList<String>() { {
                add("(?i)^" + CONNECT + " " + IP_ADDRESS + ":" + PORT + "$");
            }
        }, nextConverter);
    }

    @Override
    protected Optional<ClientResponse> doHandle(ClientRequest request) {
        return Optional.of(this.client.connect(getConnectionSettings(request.getInput())));
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
        throw new ConverterException("Unable to parse host from " + input);
    }

    private Integer getPort(String input) {
        Pattern pattern = Pattern.compile(PORT);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(0));
        }
        throw new ConverterException("Unable to parse port from " + input);
    }
}
