package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandClose extends ZorkCommandOpenClosable {

    public ZorkCommandClose(ZorkTypeGame game, String whatToClose) {
        super(game, whatToClose, "close");
    }

    @Override
    public Boolean componentAction(GameComponents component) {
        return component.close();
    }
}
