package ar.fiuba.tdd.tp.factory.input.converter.game;

import ar.fiuba.tdd.tp.client.input.command.converter.AbstractCommandConverter;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import java.util.ArrayList;
import java.util.List;

public class GameCommandConverterFactory {

    private final CommandProcessor commandProcessor;

    public GameCommandConverterFactory(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public List<AbstractCommandConverter> createGameConverters() {
        return new ArrayList<>();
    }

}
