package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandTalk extends ZorkCommandActionable {

    public ZorkCommandTalk(ZorkTypeGame game, String whoToTalkTo) {
        super(game, whoToTalkTo, "talk");
    }

    @Override
    public Boolean componentAction(GameComponents component) {
        return component.talk();
    }
}
