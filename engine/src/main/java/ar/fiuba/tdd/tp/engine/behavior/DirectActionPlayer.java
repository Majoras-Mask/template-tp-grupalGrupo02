package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class DirectActionPlayer extends DirectAction {

    public DirectActionPlayer(Game game) {
        super(game);
    }

    @Override
    ComponentInterface whereToGetThatComponent(Game game, String itemName) {
        if (game.getPlayer().playerHasItem(itemName)) {
            return game.getPlayer().obtainItemInventory(itemName);
        }
        return null;
    }
}
