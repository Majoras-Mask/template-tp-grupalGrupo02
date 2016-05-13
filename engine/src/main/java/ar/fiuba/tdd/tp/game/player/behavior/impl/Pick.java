package ar.fiuba.tdd.tp.game.player.behavior.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.context.GameContext;

import java.util.Optional;

/*
 * This behavior executes the pick operation on the given component
 */
public class Pick extends OneObjectBehaviorImpl {

    protected Pick(GameContext context) {
        super(context, "^pick .*");
    }

    @Override
    public String execute(Component component) {
        Optional<Attribute> attribute = this.getAttributeByType(component, AttributeType.PICKABLE);
        if (attribute.isPresent()) {
            this.context.addItemToPlayer(component);
            this.context.removeComponent(component);
            return "ok! " + component.getName() + " picked!";
        }

        return component.getName() + " can not be picked!";
    }

}
