package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class Drop extends RemoveInventory {
    private static final String DROP_SUCCESS = "You dropped ";
    String dropSuccess = DROP_SUCCESS;
    private static final String DROP_FAIL = "You can't drop what you don't have.";
    String dropFail = DROP_FAIL;

    public Drop(Game game, ComponentInterface item) {
        super(game, item);
    }

    @Override
    protected String whatToDoWithItem(ComponentInterface item) {
        game.getPlayer().putItemInRoom(item);
        return dropSuccess + item.getName() + ".";
    }

    @Override
    protected String failRemoving() {
        return dropFail;
    }
}
