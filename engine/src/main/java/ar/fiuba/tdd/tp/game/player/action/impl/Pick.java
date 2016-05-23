package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.Player;

/*
 * This {@link Action} executes the pick operation on the given {@link Component}
 */
public class Pick implements Action {

    private final Inventory inventory;
    private final Player player;
    private final Component component;

    public Pick(Inventory inventory, Player player, Component component) {
        this.inventory = inventory;
        this.player = player;
        this.component = component;
    }

    @Override
    public String doAction() {
        this.player.getCurrentContext().remove(component);
        this.inventory.add(component);
        return "ok! " + component.getName() + " picked!";
    }
}

