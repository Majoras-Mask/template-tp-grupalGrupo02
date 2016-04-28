package ar.fiuba.tdd.tp.motor.commands.riddle;

import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class RiddleCommandHelp extends RiddleCommand {

    public RiddleCommandHelp(GameRiddle game, String island) {
        super(game, island);
    }

    public String execute() {
        return this.riddle.help();
    }

}
