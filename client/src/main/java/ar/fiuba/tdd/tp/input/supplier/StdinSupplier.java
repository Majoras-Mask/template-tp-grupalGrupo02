package ar.fiuba.tdd.tp.input.supplier;

import ar.fiuba.tdd.tp.input.command.InputCommand;
import ar.fiuba.tdd.tp.input.converter.InputConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StdinSupplier implements CommandSupplier {

    private final BufferedReader inputBuffer;
    private final InputConverter converter;

    public StdinSupplier(InputConverter converter) {
        this.inputBuffer = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        this.converter = converter;
    }

    @Override
    public InputCommand getCommand() {
        try {
            return this.converter.convert(this.inputBuffer.readLine());
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error while reading from input buffer: " + e);
        }
    }

}
