package ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.GameHanoi;

/**
 * Created by kevin on 22/04/16.
 */
public class GameCommandCheckSize implements GameCommand {
    private GameHanoi game;
    private int stackIndex;

    public GameCommandCheckSize(GameHanoi game, int stackIndex) {
        this.game = game;
        this.stackIndex = stackIndex;
    }

    @Override
    public String execute() {
        int size = this.game.getTopElementFromStack(this.stackIndex);
        String toReturn = "Size of top from stack " + this.stackIndex + "is " + size;
        return toReturn;
    }
}
