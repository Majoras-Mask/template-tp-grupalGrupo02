package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandOpen extends ZorkCommandActionable {

    public ZorkCommandOpen(ZorkTypeGame game, String whatToOpen) {
        super(game, whatToOpen);
    }

    @Override
    public String execute() {
        return this.game.open(this.whoToDoActionWith);
    }
}