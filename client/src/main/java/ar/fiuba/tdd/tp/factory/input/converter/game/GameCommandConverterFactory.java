package ar.fiuba.tdd.tp.factory.input.converter.game;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.converter.InputConverter;
import ar.fiuba.tdd.tp.input.converter.game.GameCommandConverter;

public class GameCommandConverterFactory {

    private final CommandProcessor commandProcessor;

    public GameCommandConverterFactory(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public InputConverter createGameConverters() {
        return new GameCommandConverter(this.commandProcessor);
    }

}
