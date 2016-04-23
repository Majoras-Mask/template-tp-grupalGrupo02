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
        pattern = Pattern.compile("connect ((([0-9])|([1-9][0-9])|(1[0-9]{2})|(2[0-4][0-9])|(25[0-5]))\\.){3}(([0-9])|([1-9][0-9])|(1[0-9]{2})|(2[0-4][0-9])|(25[0-5])):(([0-9])|([1-9][0-9])|([1-9][0-9]{2})|([1-9][0-9]{3})|([1-5][0-9]{4})|(6[0-4][0-9]{3})|(65[0-4][0-9]{2})|(655[0-2][0-9])|(6553[0-5]))");
        socket = null;
        connected = false;
    }

    public IpPort readEntry() {
        String input;
        try {
            input = inputBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return new NullIpPort();
        }
        if (input == null) {
            return new NullIpPort();
        }
        return readInput(input);
    }

    private IpPort readInput(String input) {
        Matcher matcher = pattern.matcher(input);
        if (input.equals("exit")) {
            return new NullIpPort();
        }
        if (matcher.find()) {
            return new ValidIpPort(input.substring(input.indexOf(' ') + 1,input.indexOf(':')), input.substring(input.indexOf(':') + 1));
        } else {
            System.out.println("Client> That's not the correct command! please type [connect [0-255].[0-255].[0-255].[0-255]:[0-65535]] to an opened port");
            NullIpPort nullIpPort = new NullIpPort();
            nullIpPort.validate();
            return nullIpPort;
        }
    }

    public void init() {
        System.out.println("Client> Welcome to Majora's Mask game service, please connect to the server typing 'connect [0-255].[0-255].[0-255].[0-255]:[0-65535]' (try 127.0.0.1:8000)");
    }

    public boolean connected() {
        return connected;
    }

    public void tryConnect(IpPort ipPort) {
        try {
            socket = new Socket(ipPort.getIp(), Integer.parseInt(ipPort.getPort()));
            connected = true;
            clientOutput = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (ConnectException e) {
            System.out.println("Client> Connection refused, try with 127.0.0.1:8000");
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
