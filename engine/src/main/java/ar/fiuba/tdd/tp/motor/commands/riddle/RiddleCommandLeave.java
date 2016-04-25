package ar.fiuba.tdd.tp.motor.commands.riddle;

import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class RiddleCommandLeave extends RiddleCommand {

    public RiddleCommandLeave(GameRiddle game, String animal) {
        super(game, animal);
    }

    @Override
    public String execute() {

        if (riddle.canLeave(component)) {
            riddle.leaveAnimal();
            return "Ok.";
        } else {
            return "You can’t do that! There's no " + component + " on the boat.";
        }
    }
}
