package ar.fiuba.tdd.tp.engine.player.action.impl;

import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.player.Player;

/*
 * This {@link Action} executes the pick operation on the given {@link Component}
 */
public class Pick implements Action {

    private final Player player;
    private final Component component;

    public Pick(Player player, Component component) {
        this.player = player;
        this.component = component;
    }

    @Override
    public String doAction() {
        this.player.getCurrentContext().remove(component);
        this.player.getInventory().add(component);
        return "ok! " + component.getName() + " picked!";
    }
}

