package ar.fiuba.tdd.tp.game.component.attribute.pick;

import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;

public class PickableImpl implements PickableAttribute {

    @Override
    public AttributeType getType() {
        return AttributeType.OPENABLE;
    }

    @Override
    public void pick() {

    }
}
