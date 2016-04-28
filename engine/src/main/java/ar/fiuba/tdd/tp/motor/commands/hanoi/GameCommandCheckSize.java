package ar.fiuba.tdd.tp.motor.commands.hanoi;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;

/**
 * Created by kevin on 22/04/16.
 */
public class GameCommandCheckSize implements GameCommand {
    private GameHanoi game;
    private int stackIndex;

    public GameCommandCheckSize(GameHanoi game, int stackIndex) {
        this.game = game;
        this.stackIndex = stackIndex - 1;
    }

    @Override
    public String execute() {
        String toReturn;
        if (this.game.isValidIndex(stackIndex) && this.game.getSize(stackIndex) > 0) {
            int size = this.game.getTopElementFromStack(this.stackIndex);
            toReturn = "Size of top from stack " + (this.stackIndex + 1) + " is " + size;
        } else {
            return "There isn't element in this stack";
        }
        return toReturn;
    }
}
