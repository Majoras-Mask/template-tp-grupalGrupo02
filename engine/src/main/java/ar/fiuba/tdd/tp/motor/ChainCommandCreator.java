package ar.fiuba.tdd.tp.motor;

import ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi.UtilityParser;

/**
 * Created by kevin on 22/04/16.
 */
public abstract class ChainCommandCreator {

    protected ChainCommandCreator nextChain;
    private String patternString;

    protected void loadPatternString(String patternString) {
        this.patternString = patternString;
    }

    protected String getPatternString() {
        return this.patternString;
    }

    public void setNextChain(ChainCommandCreator chain) {
        this.nextChain = chain;
    }

    private boolean checkIfMatches(String message) {
        return UtilityParser.matches(patternString, message);
    }

    protected abstract GameCommand factoryCommand(String message);

    public GameCommand createCommand(String message) {
        if (checkIfMatches(message)) {
            return this.factoryCommand(message);
        }

        if (this.nextChain != null) {
            return this.nextChain.createCommand(message);
        } else {
            return new GameCommandNull();
        }
    }

}
