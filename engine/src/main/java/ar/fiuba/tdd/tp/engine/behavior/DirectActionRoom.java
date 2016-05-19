package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class DirectActionRoom extends DirectAction {

    public DirectActionRoom(Game game) {
        super(game);
    }

    @Override
    ComponentInterface whereToGetThatComponent(Game game, String itemName) {
        return game.getPlayer().obtainItemRoom(itemName);
    }
}
