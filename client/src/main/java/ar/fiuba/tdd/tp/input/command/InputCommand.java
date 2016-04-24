package ar.fiuba.tdd.tp.input.command;

import ar.fiuba.tdd.tp.output.ClientResponse;

public interface InputCommand {
    ClientResponse execute();
}
