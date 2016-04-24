package ar.fiuba.tdd.tp.motor.chains.hanoi;

import ar.fiuba.tdd.tp.motor.UtilityParser;
import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.commands.hanoi.GameCommandCheckSize;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;


public class ChainCheckSize extends ChainHanoi{

    public ChainCheckSize(GameHanoi gameHanoi, String patternString) {
        super(gameHanoi, patternString);
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        // pattern style ".* (\\d)"
        String group1 = UtilityParser.getGroup(this.getPatternString(), message, 1);
        int stackIndex = Integer.parseInt(group1);
        return new GameCommandCheckSize(gameHanoi,stackIndex);

    }

}
