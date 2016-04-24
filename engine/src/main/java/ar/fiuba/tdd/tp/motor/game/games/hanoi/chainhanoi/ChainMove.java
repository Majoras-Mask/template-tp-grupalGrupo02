package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi.GameCommandMove;

public class ChainMove extends ChainHanoi {
    public ChainMove(GameHanoi gameHanoi, String patternString) {
        super(gameHanoi, patternString);
    }

    private int getIntegerFromGroup(String message, int group) {
        return Integer.parseInt(UtilityParser.getGroup(this.getPatternString(), message, group));
    }

    @Override
    protected GameCommand factoryCommand(String message) {
        // patternString style ".* (\\d) stack (\\d)";
        int fromStack = getIntegerFromGroup(message, 1);
        int toStack = getIntegerFromGroup(message, 2);
        return new GameCommandMove(this.gameHanoi,fromStack, toStack);
    }
}



