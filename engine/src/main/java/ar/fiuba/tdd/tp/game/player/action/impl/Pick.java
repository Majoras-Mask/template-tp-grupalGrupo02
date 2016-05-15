package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This behavior executes the pick operation on the given component
 */
public class Pick extends OneObjectAction {

    private final Inventory inventory;

    public Pick(Inventory inventory, Context context) {
        super(context, "^pick ");
        this.inventory = inventory;
    }

    @Override
    public String execute(Component component) {
        Optional<Attribute> attribute = this.getAttributeByType(component, AttributeType.PICKABLE);
        if (attribute.isPresent()) {
            return this.doPick(component);
        }

        return component.getName() + " can not be picked!";
    }

    private String doPick(Component component) {
        this.context.remove(component);
        this.inventory.add(component);

        return "ok! " + component.getName() + " picked!";
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }
}
