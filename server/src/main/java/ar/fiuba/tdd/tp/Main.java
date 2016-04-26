package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.api.Server;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
            server.run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /*int portNumber = 8000;
​
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("port " + Integer.toString(portNumber) + " opened");
        Socket clientSocket = serverSocket.accept();
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8")), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
​
        String inputLine;
        String outputLine;
​
        // Initiate conversation with client
        outputLine = "talk to me my son";
        out.println(outputLine);
​
        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client> " + inputLine);
                outputLine = consoleInput.readLine();
                out.println(outputLine);
                if (outputLine == null || outputLine.equals("close")) {
                    break;
                }
            }
        } catch (SocketException e) {
            System.out.println("Client disconnected");
        }*/
    }
}