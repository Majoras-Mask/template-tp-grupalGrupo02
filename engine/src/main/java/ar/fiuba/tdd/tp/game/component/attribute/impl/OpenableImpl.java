package ar.fiuba.tdd.tp.game.component.attribute.impl;

import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.OpenableAttribute;

public class OpenableImpl implements OpenableAttribute {

    private Boolean isOpen;

    public OpenableImpl() {
        this.isOpen = Boolean.FALSE;
    }

    public OpenableImpl(Boolean isOpen) {
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
