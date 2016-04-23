package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkTypeCommandOpen extends ZorkTypeCommand implements GameCommand {

    public ZorkTypeCommandOpen(ZorkTypeGame game, String whatToOpen) {
        super(game, whatToOpen);
    }

    @Override
    public String execute() {
        return this.game.open(this.receiverOfAction);

    }
}
