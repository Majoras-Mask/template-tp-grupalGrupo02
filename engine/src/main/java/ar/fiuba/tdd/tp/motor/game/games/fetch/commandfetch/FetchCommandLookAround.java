package ar.fiuba.tdd.tp.motor.game.games.fetch.commandfetch;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.fetch.GameFetch;

public class FetchCommandLookAround implements GameCommand {
    private GameFetch game;

    public FetchCommandLookAround(GameFetch gameFetch) {
        this.game = gameFetch;
    }

    @Override
    public String execute() {
        if (this.game.getStickInPlayer()) {
            return "It's an empty room.";
        } else {
            return "There’s a stick in the room.";
        }
    }
}
