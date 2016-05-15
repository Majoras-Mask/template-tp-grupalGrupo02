package ar.fiuba.tdd.tp.game.component.attribute.impl;

import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.Pickable;

public class PickableImpl implements Pickable {

    private Integer weight;

    public PickableImpl() {
        this(0);
    }

    public PickableImpl(Integer weight) {
        this.weight = weight;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.PICKABLE;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }
}
