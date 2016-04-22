package ar.fiuba.tdd.tp.motor.games.hanoi.commandhanoi;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.games.hanoi.InvalidOperation;

/**
 * Created by kevin on 22/04/16.
 */
public class GameCommandMove implements GameCommand {

    private GameHanoi game;
    private int fromStack;
    private int toStack;

    public GameCommandMove(GameHanoi game, int fromStack, int toStack) {
        this.game = game;
        this.fromStack = fromStack;
        this.toStack = toStack;
    }

    @Override
    public String execute() {
        try {
            this.game.move(this.fromStack, this.toStack);
            return "moved!";
        } catch (InvalidOperation e) {
            return "Error: Movimiento invalido";
        }
    }
}
