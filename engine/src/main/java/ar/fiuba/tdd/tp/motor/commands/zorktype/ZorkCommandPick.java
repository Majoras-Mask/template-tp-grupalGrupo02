package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandPick extends ZorkCommandActionable {

    public ZorkCommandPick(ZorkTypeGame game, String whatToPick) {
        super(game, whatToPick);
    }

    @Override
    public String execute() {
        return this.game.pick(this.whoToDoActionWith);
    }
}
