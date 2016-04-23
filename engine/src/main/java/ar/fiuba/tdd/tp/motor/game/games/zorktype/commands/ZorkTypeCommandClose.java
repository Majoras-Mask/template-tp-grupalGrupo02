package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;

import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkType;

public class ZorkTypeCommandClose extends ZorkTypeCommand implements GameCommand {

    public ZorkTypeCommandClose(ZorkType game, String whatToClose) {
        super(game, whatToClose);
    }

    @Override
    public String execute() {
        return this.game.close(this.receiverOfAction);
    }
}
