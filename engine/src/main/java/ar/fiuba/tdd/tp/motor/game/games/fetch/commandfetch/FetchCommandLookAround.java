package ar.fiuba.tdd.tp.motor.game.games.fetch.commandfetch;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.fetch.GameFetch;

public class FetchCommandLookAround implements GameCommand {
    public FetchCommandLookAround() {
    }

    @Override
    public String execute() {
        return "There’s a stick in the room.";
    }
}
