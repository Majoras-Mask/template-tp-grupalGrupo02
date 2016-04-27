package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandTalk extends ZorkCommandActionable {

    public ZorkCommandTalk(ZorkTypeGame game, String whoToTalkTo) {
        super(game, whoToTalkTo);
    }

    @Override
    public String execute() {
        return this.game.talk(this.whoToDoActionWith);
    }
}
