package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public abstract class RemoveInventory implements Behavior {
    Game game;
    ComponentInterface item;

    public RemoveInventory(Game game, ComponentInterface item) {
        this.game = game;
        this.item = item;
    }

    public String execute(String modifier) {
        if (game.getPlayer().playerHasItem(item)) {
            game.getPlayer().removeItem(item);
            return whatToDoWithItem(item);
        }
        return failRemoving();
    }

    protected abstract String whatToDoWithItem(ComponentInterface item);

    protected abstract String failRemoving();
}
