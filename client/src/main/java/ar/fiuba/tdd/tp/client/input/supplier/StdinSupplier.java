package ar.fiuba.tdd.tp.client.input.supplier;

import ar.fiuba.tdd.tp.client.exception.InputException;
import ar.fiuba.tdd.tp.client.input.ClientRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StdinSupplier implements ClientSupplier {

    private final BufferedReader inputBuffer;

    public StdinSupplier() {
        this.inputBuffer = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public ClientRequest getRequest() {
        try {
            return new ClientRequest(this.inputBuffer.readLine());
        } catch (Exception e) {
            throw new InputException("Can't read from the standard input stream: " + e.getLocalizedMessage());
        }
    }

}
