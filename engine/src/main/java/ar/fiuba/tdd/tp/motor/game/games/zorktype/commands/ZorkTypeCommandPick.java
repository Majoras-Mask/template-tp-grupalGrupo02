package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkTypeCommandPick extends ZorkTypeCommand implements GameCommand {

    public ZorkTypeCommandPick(ZorkTypeGame game, String whatToOpen) {
        super(game, whatToOpen);
    }

    @Override
    public String execute() {
        return this.game.pick(this.receiverOfAction);
    }
}
