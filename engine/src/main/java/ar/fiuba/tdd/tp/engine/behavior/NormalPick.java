package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class NormalPick implements Behavior {
    Game game;
    ComponentInterface item;
    private String cantPick;
    private String pickSuccess;
    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";

    public NormalPick(Game game, ComponentInterface item) {
        this.game = game;
        this.item = item;
        cantPick = CANT_PICK;
        pickSuccess = PICK_SUCCESS;
    }

    public String execute(String modifier) {
        if (this.game.getPlayer().removeItemFromRoom(item)) {
            this.game.getPlayer().addItemToInventory(item);
            return pickSuccess + item.getName();
        }
        return cantPick;
    }
}
