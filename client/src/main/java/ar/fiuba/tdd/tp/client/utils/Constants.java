package ar.fiuba.tdd.tp.client.utils;

import ar.fiuba.tdd.tp.client.output.ClientResponse;

public class Constants {

    public static final ClientResponse CONNECTION_SUCCESSFUL = new ClientResponse("The connection was successful! "
            + "Type what you want to do...");

    public static final ClientResponse CLOSE_SUCCESSFUL = new ClientResponse("Connection with server closed!");

    public static final ClientResponse UNEXPECTED_ERROR = new ClientResponse("Something really bad happened "
            + "while connecting with the server. I'm sorry, but you'll have to go and play somewhere else..");

    public static final ClientResponse WELCOME = new ClientResponse("Welcome!!");

    public static final String PROCESS_COMMAND_ERROR = "Something bad occurred with your command: ";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

}
