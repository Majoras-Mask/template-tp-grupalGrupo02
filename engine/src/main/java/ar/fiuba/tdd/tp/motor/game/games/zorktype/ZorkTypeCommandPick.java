package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.GameCommandWithModifier;

public class ZorkTypeCommandPick implements GameCommandWithModifier {
    private ZorkType game;

    public ZorkTypeCommandPick(ZorkType game) {
        this.game = game;
    }

    @Override
    public String execute(String whatToPick) {
        return this.game.pick(whatToPick);
    }
}
