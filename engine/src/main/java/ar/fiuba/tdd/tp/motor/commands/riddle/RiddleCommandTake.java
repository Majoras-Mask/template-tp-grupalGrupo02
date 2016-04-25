package ar.fiuba.tdd.tp.motor.commands.riddle;

import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class RiddleCommandTake extends RiddleCommand {

    public RiddleCommandTake(GameRiddle gameRiddle, String animal) {
        super(gameRiddle,animal);
    }

    @Override
    public String execute() {
        if (riddle.canTake(component)) {
            riddle.take(component);
            return "Ok.";
        } else if (riddle.boatFull()) {
            return "You can’t do that! The boat is full.";
        }
        return "You can’t do that! There's no " + component + " on the island.";
    }
}
