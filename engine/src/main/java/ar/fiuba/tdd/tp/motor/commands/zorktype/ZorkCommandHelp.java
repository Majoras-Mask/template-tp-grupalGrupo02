package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandHelp implements GameCommand {
    private ZorkTypeGame game;

    public ZorkCommandHelp(ZorkTypeGame game) {
        this.game = game;
    }

    @Override
    public String execute() {
        return this.game.help();
    }
}
