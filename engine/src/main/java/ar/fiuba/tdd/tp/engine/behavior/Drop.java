package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class Drop implements Behavior {

    private static final String DROP_SUCCESS = "You dropped ";
    private static final String DROP_FAIL = "You can't drop what you don't have.";

    Game game;
    ComponentInterface item;
    String dropSuccess = DROP_SUCCESS;
    String dropFail = DROP_FAIL;

    public Drop(Game game, ComponentInterface item) {
        this.game = game;
        this.item = item;
    }

    public String execute(String modifier) {
        if (game.getPlayer().playerHasItem(item)) {
            game.getPlayer().putItemInRoom(item);
            game.getPlayer().removeItem(item);
            return dropSuccess + item.getName() + ".";
        }
        return dropFail;
    }
}
