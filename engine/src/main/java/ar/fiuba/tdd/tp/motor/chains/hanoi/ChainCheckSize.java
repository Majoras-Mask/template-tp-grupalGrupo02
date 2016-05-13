package ar.fiuba.tdd.tp.motor.chains.hanoi;

import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.hanoi.GameCommandCheckSize;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;


public class ChainCheckSize extends ChainHanoi {

    public ChainCheckSize(GameHanoi gameHanoi, String patternString) {
        super(gameHanoi, patternString);
    }

    private int getIndexFromMessage(String message) {
        String group1 = UtilityParser.getGroup(this.getPatternString(), message, 1);
        return Integer.parseInt(group1);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        // pattern style ".* (\\d)"
        return new GameCommandCheckSize(gameHanoi,getIndexFromMessage(message));
    }

}
