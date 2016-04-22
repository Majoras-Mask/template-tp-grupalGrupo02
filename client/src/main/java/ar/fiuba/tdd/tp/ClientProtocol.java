package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manuelcruz on 21/04/2016.
 */
public class ClientProtocol {
    private BufferedReader inputBuffer;
    private Pattern pattern;
    private Socket socket;
    private boolean connected;
    PrintWriter clientOutput;
    BufferedReader serverInput;

    public ClientProtocol() throws UnsupportedEncodingException {
        inputBuffer = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        pattern = Pattern.compile("connect localhost:[0-9]{4}");
        socket = null;
        connected = false;

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

    private int readInput(String input) {
        Matcher matcher = pattern.matcher(input);
        if (input.equals("exit")) {
            return 0;
        }
        if (matcher.find()) {
            return Integer.parseInt(input.substring(18));
        } else {
            System.out.println("Client> That's not the correct command! please type [connect localhost:port] to an opened port");
            return -1;
        }
    }

    public void init() {
        System.out.println("Client> Welcome to Majora's Mask game service, please connect to the server typing [connect localhost:port] to an opened port");
    }

    public boolean connected() {
        return connected;
    }

    public void tryConnect(int port) {
        try {
            socket = new Socket("localhost", port);
            connected = true;
            clientOutput = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (ConnectException e) {
            System.out.println("Client> That port is closed! try another one");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean listen() {
        String input = "close";
        try {
            input = serverInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input == null || input.equals("close")) {
            System.out.println("Disconnected from server");
            return false;
        }
        System.out.println("Server> " + input);
        return true;
    }

    public void sendEntry() {
        try {
            String input = inputBuffer.readLine();
            clientOutput.println(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
