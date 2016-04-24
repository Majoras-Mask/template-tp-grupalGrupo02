package ar.fiuba.tdd.tp.client.input.command;

import ar.fiuba.tdd.tp.client.output.ClientResponse;

public interface Command {
    ClientResponse execute();
}
