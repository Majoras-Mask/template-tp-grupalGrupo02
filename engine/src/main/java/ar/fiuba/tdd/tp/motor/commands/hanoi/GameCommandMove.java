package ar.fiuba.tdd.tp.motor.commands.hanoi;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;

public class GameCommandMove implements GameCommand {

    private GameHanoi game;
    private int fromStack;
    private int toStack;

    public GameCommandMove(GameHanoi game, int fromStack, int toStack) {
        this.game = game;
        this.fromStack = fromStack - 1;
        this.toStack = toStack - 1;
    }

    @Override
    public String execute() {
        if (this.game.isValidMove(this.fromStack, this.toStack)) {
            this.game.move(this.fromStack, this.toStack);
            return "moved!";
        }
        return "Movimiento invalido";
    }
}
