package ar.fiuba.tdd.tp.output.consumer;

import ar.fiuba.tdd.tp.output.ClientResponse;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class SysoutPrinter implements ClientConsumer {

    private static final String PREFIX = "CommandProcessor> ";

    private final BufferedWriter outputBuffer;

    public SysoutPrinter() {
        this.outputBuffer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    }

    @Override
    public void consume(ClientResponse response) {
        try {
            this.outputBuffer.write(PREFIX + response.getEvent());
            this.outputBuffer.flush();
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error occurred while writing to output buffer: " + e);
        }
    }
}
