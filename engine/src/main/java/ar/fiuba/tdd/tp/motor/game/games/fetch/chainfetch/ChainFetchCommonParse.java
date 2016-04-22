package ar.fiuba.tdd.tp.motor.game.games.fetch.chainfetch;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.fetch.GameFetch;

import java.util.regex.Pattern;

public abstract class ChainFetchCommonParse extends ChainCommandCreator {
    public abstract GameCommand factoryCommand(String message);

    protected String patternString;
    protected GameFetch gameFetch;

    public ChainFetchCommonParse(GameFetch gameFetch, String patternString) {
        this.gameFetch = gameFetch;
        this.patternString = patternString;
    }

    public boolean checkCondition(String message) {
        return Pattern.compile(patternString).matcher(message.toLowerCase()).find();
    }

    public GameCommand createCommand(String message) {
        if (checkCondition(message)) {
            return factoryCommand(message);
        }
        return super.createCommand(message);
    }
}
