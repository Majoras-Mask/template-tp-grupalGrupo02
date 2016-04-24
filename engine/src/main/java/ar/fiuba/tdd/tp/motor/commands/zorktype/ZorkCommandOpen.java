package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandOpen extends ZorkCommandActionable {

    public ZorkCommandOpen(ZorkTypeGame game, String whatToOpen) {
        super(game, whatToOpen, "open");
    }

    @Override
    public Boolean componentAction(GameComponents component) {
        return component.open();
    }
}
