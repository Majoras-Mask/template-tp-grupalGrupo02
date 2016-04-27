package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ZorkCommandActionable extends ZorkCommand {
    public final String whoToDoActionWith;

    public ZorkCommandActionable(ZorkTypeGame game, String whoToDoActionWith) {
        this.game = game;
        this.whoToDoActionWith = whoToDoActionWith;
    }
}
