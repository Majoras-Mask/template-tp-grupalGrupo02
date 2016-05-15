package ar.fiuba.tdd.tp.game.component.attribute.impl;

import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.Pickable;

public class PickableImpl implements Pickable {

    @Override
    public AttributeType getType() {
        return AttributeType.PICKABLE;
    }

    @Override
    public void pick() {

    }
}
