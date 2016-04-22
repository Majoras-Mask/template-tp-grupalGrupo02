package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi.GameCommandMove;

public class ChainMove extends ChainCommandCreator {
    private GameHanoi game;

    public ChainMove(GameHanoi game) {
        this.game = game;
    }

    public GameCommand createCommandFromMessage(String message, String patternString) {
        String group1 = UtilityParser.getGroup(patternString, message, 1);
        String group2 = UtilityParser.getGroup(patternString, message, 2);
        int fromStack = Integer.parseInt(group1);
        int toStack = Integer.parseInt(group2);
        return new GameCommandMove(game,fromStack, toStack);
    }

    @Override
    public GameCommand createCommand(String message) {
        String patternString = "move top stack (\\d) stack (\\d)";
        if ( UtilityParser.matches(patternString, message) ) {
            return createCommandFromMessage(message, patternString);
        } else {
            return super.createCommand(message);
        }
    }
}



