package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkType;

public class ZorkTypeCommandPick extends ZorkTypeCommand implements GameCommand {

    public ZorkTypeCommandPick(ZorkType game, String whatToOpen) {
        super(game, whatToOpen);
    }

    @Override
    public String execute() {
        return this.game.pick(this.receiverOfAction);
    }
}
