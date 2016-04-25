package ar.fiuba.tdd.tp.motor.commands.riddle;

import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public class RiddleCommandCross extends RiddleCommand {

    public RiddleCommandCross(GameRiddle game, String island) {
        super(game, island);
    }

    private void cross() {
        riddle.cross(component);
    }

    @Override
    public String execute() {
        if (riddle.canCross(component)) {
            cross();
            return "You have crossed!";
        } else if (riddle.wolfAndSheepTogether()) {
            return "You can’t do that! The wolf will eat the sheep!";
        } else if (riddle.sheepAndCabbageTogether()) {
            return "You can’t do that! The sheep will eat the cabbage!";
        }
        return "You can’t do that! You are already on the " + component + " island.";

    }
}
