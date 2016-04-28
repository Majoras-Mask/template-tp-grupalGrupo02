package ar.fiuba.tdd.tp.server.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ServerOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Integer port;

    @Before
    public void init() {
        port = 8001;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void clientConnectedTest() {
        ServerOutput.clientConnected(port);

        assertEquals("Server> Client connected on port " + port + '\n',outContent.toString());
    }

    @Test
    public void clientDisconnectedTest() {
        ServerOutput.clientDisconnected(port);

        assertEquals("Server> Offline client on port " + port + '\n',outContent.toString());
    }

    @Test
    public void welcomeMessageTest() {
        ServerOutput.welcomeMessage();

        assertEquals("Server> Welcome to Majora's Mask game service, open a new game typing 'load game'" + '\n',outContent.toString());
    }

    @Test
    public void newGameTest() {
        ServerOutput.newGame(port);

        assertEquals("Server> New game opened at port " + port + '\n',outContent.toString());
    }

    @Test
    public void noPortsAvailableTest() {
        ServerOutput.noPortsAvailable();

        assertEquals("Server> No ports available" + '\n',outContent.toString());
    }

    @Test
    public void unknownCommandTest() {
        ServerOutput.unknownCommand();

        assertEquals("Server> Unknown command, try with 'close' 'exit' or 'connect'" + '\n',outContent.toString());
    }

    @Test
    public void unopenedConnectionTest() {
        ServerOutput.unopenedConnection(port);

        assertEquals("Server> Couldn't open connection at port " + port + '\n',outContent.toString());
    }

    @Test
    public void choosePortTest() {
        ServerOutput.choosePort();

        assertEquals("Server> Please choose a port to close" + '\n',outContent.toString());
    }

    @Test
    public void closedPort() {
        ServerOutput.closedPort(port);

        assertEquals("Server> The port " + port + " is now closed" + '\n',outContent.toString());
    }

    @Test
    public void unreachedPortTest() {
        ServerOutput.unreachedPort(port);

        assertEquals("Server> Could not close port " + port + '\n',outContent.toString());
    }

}
