package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi.GameCommandCheckSize;

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
