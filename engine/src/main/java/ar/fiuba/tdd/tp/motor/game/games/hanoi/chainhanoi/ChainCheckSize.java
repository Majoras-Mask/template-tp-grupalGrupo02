package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi.GameCommandCheckSize;

public class ChainCheckSize extends ChainCommandCreator{

    private GameHanoi gameHanoi;

    public ChainCheckSize(GameHanoi gameHanoi) {
        this.gameHanoi = gameHanoi;
    }


    @Override
    public GameCommand createCommand(String message) {
        String patternString = "check top stack (\\d)";
        if ( UtilityParser.matches(patternString, message) ) {
            String group1 = UtilityParser.getGroup(patternString, message, 1);
            int stackIndex = Integer.parseInt(group1);
            return new GameCommandCheckSize(gameHanoi,stackIndex);
        } else {
            return super.createCommand(message);
        }
    }

}
