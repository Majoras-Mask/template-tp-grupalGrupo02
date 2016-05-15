package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.open.OpenableAttribute;
import ar.fiuba.tdd.tp.game.context.GameContext;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.OneObjectActionImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This behavior executes the open operation on the given component
 */
public class Open extends OneObjectActionImpl {

    protected Open(GameContext context) {
        super(context, "^open .*");
    }

    @Override
    public String execute(Component component) {
        Optional<Attribute> attribute = this.getAttributeByType(component, AttributeType.OPENABLE);
        if (!attribute.isPresent()) {
            return component.getName() + " can not be open!";
        }

        return doOpen((OpenableAttribute) attribute.get());
    }

    private String doOpen(OpenableAttribute openableAttribute) {
        if (openableAttribute.isOpen()) {
            return "This was already open!";
        }

        openableAttribute.open();
        return "Opened!";
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }
}
