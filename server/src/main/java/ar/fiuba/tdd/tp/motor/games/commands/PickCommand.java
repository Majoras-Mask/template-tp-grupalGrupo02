package ar.fiuba.tdd.tp.motor.games.commands;

import ar.fiuba.tdd.tp.motor.games.Game;

public class PickCommand implements GameCommand {
    private Game game;

    public PickCommand(Game game) {
        this.game = game;
    }

    public void execute(String executorModificator) {
        game.pick(executorModificator);
    }
}
