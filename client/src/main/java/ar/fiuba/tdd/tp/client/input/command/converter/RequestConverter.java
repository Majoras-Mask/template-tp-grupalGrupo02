package ar.fiuba.tdd.tp.client.input.command.converter;

import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.Command;

public interface RequestConverter {
    Command convert(ClientRequest request);
}
