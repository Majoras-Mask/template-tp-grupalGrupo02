package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.List;

import static ar.fiuba.tdd.tp.game.player.action.ActionType.PICK;

/*
 * This {@link Action} executes the pick operation on the given {@link Component}
 */
public class Pick extends OneObjectAction {

    private final Inventory inventory;


    public Pick(Inventory inventory, Player player, String pattern) {
        super(player, "^" + pattern + " ");
        this.inventory = inventory;
    }

    @Override
    public String doExecute(Component component) {
        return (component.supports(PICK)) ? doPick(component) : canNotPick(component);
    }

    private String canNotPick(Component component) {
        return component.getName() + " can not be picked";
    }

    private String doPick(Component component) {
        ActionResponse actionResponse = component.doAction(new ActionRequest(PICK));
        if (actionResponse.success()) {
            this.player.getCurrentContext().remove(component);
            this.inventory.add(component);
            return "ok! " + component.getName() + " picked!";
        }

        return this.canNotPick(component) + ": " + actionResponse.getMessage();
    }
}
