package ar.fiuba.tdd.tp.input.converter;

import ar.fiuba.tdd.tp.input.command.InputCommand;

public interface InputConverter {
    InputCommand convert(String input);
}
