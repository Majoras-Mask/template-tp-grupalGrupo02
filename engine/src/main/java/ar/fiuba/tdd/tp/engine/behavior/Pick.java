package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class Pick implements Behavior {
    Game game;
    ComponentInterface item;
    private String cantPick = CANT_PICK;
    private String pickSuccess = PICK_SUCCESS;
    private String inventoryFull = INVENTORY_FULL;
    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";
    private static final String INVENTORY_FULL = "You can't carry anymore, inventory full.";

    public Pick(Game game, ComponentInterface item) {
        this.game = game;
        this.item = item;
    }

    public String execute(String modifier) {
        if (game.getPlayer().inventoryFull()) {
            return inventoryFull;
        }
        if (game.getPlayer().removeItemFromRoom(item)) {
            game.getPlayer().addItemToInventory(item);
            return pickSuccess + item.getName();
        }
        return cantPick;
    }
}
