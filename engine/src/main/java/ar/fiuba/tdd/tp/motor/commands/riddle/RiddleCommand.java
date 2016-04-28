package ar.fiuba.tdd.tp.motor.commands.riddle;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;

public abstract class RiddleCommand implements GameCommand {

    protected GameRiddle riddle;
    protected String component;

    public RiddleCommand(GameRiddle game, String comp) {
        this.riddle = game;
        component = comp;
    }

}
