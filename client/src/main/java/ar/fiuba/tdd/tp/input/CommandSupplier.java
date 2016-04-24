package ar.fiuba.tdd.tp.input;

import ar.fiuba.tdd.tp.input.command.InputCommand;

public interface CommandSupplier {
    InputCommand getNext();
}
