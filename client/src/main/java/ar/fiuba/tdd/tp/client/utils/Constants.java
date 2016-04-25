package ar.fiuba.tdd.tp.client.utils;

import ar.fiuba.tdd.tp.client.output.ClientResponse;

public class Constants {

    public static final ClientResponse CONNECTION_SUCCESSFUL = new ClientResponse("The connection was successful! "
            + "Type what you want to do...");

    public static final ClientResponse CLOSE_SUCCESSFUL = new ClientResponse("CYA!");

    public static final ClientResponse WELCOME = new ClientResponse("Welcome!!");

    public static final String PROCESS_COMMAND_ERROR = "Something bad occurred with your command: ";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static final String ANOTHER_OPEN_CONNECTION = "Another connection is open. Close it with 'exit'";

    public static final String OPEN_CONNECTION_FIRST = "Open a connection first!";
}
