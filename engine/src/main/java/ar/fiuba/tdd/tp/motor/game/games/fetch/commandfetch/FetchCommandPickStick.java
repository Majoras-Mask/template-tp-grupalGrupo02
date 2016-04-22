package ar.fiuba.tdd.tp.motor.game.games.fetch.commandfetch;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.fetch.GameFetch;

public class FetchCommandPickStick implements GameCommand {
    private GameFetch game;

    public FetchCommandPickStick(GameFetch gameFetch) {
        this.game = gameFetch;
    }

    @Override
    public String execute() {
        this.game.pickStick();

        if (this.game.checkIfGameIsFinished()) {
            return "You won the games!";
        } else {
            return "You picked the stick.";
        }

    }
}
