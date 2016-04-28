package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandConsume extends ZorkCommandActionable {

    public ZorkCommandConsume(ZorkTypeGame game, String whatToConsume) {
        super(game, whatToConsume);
    }

    @Override
    public String execute() {
        return this.game.consume(this.whoToDoActionWith);
    }

}
