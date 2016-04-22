package ar.fiuba.tdd.tp.motor.games.commands;

public class PickCommand implements GameCommand {
    private Pick game;

    public PickCommand(Pick game) {
        this.game = game;
    }

    public void execute(String executorModificator) {
        game.pick(executorModificator);
    }
}
