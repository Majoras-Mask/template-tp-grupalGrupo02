package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.Openable;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This behavior executes the open operation on the given component
 */
public class Open extends OneObjectAction {

    public Open(Context context) {
        super(context, "^open .*");
    }

    @Override
    public String execute(Component component) {
        Optional<Attribute> attribute = this.getAttributeByType(component, AttributeType.OPENABLE);
        if (!attribute.isPresent()) {
            return component.getName() + " can not be open!";
        }

        return doOpen((Openable) attribute.get());
    }

    private String doOpen(Openable openable) {
        if (openable.isOpen()) {
            return "Is already open!";
        }

        openable.open();
        return "Opened!";
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }
}
