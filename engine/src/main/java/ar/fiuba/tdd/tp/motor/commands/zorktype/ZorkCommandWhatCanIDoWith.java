package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandWhatCanIDoWith extends ZorkCommandActionable {

    public ZorkCommandWhatCanIDoWith(ZorkTypeGame game, String receiverOfAction) {
        super(game, receiverOfAction);
    }

    @Override
    public String execute() {
        return this.game.whatCanIDoWith(this.whoToDoActionWith);
    }
}
