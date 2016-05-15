package ar.fiuba.tdd.tp.game.component.attribute.impl;

import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.Openable;

public class LockableImpl implements Openable {

    private Boolean isOpen;

    public LockableImpl() {
        this.isOpen = Boolean.FALSE;
    }

    public LockableImpl(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.OPENABLE;
    }

    @Override
    public Boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public void open() {
        this.isOpen = Boolean.TRUE;
    }

}
