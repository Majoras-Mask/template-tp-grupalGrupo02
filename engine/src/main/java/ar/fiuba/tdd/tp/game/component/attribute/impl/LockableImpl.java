package ar.fiuba.tdd.tp.game.component.attribute.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.component.attribute.Lockable;

public class LockableImpl implements Lockable {

    private final Component key;
    private final OpenableImpl openable;
    private Boolean isLocked;

    public LockableImpl(Component key) {
        this.key = key;
        this.openable = new OpenableImpl();
        this.isLocked = Boolean.FALSE;
    }

    public LockableImpl(Component key, Boolean isOpen) {
        this.key = key;
        this.openable = new OpenableImpl(isOpen);
        this.isLocked = Boolean.FALSE;
    }

    public LockableImpl(Component key, Boolean isOpen, Boolean isLocked) {
        this.key = key;
        this.openable = new OpenableImpl(isOpen);
        this.isLocked = isLocked;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.LOCKABLE;
    }

    @Override
    public Boolean isOpen() {
        return this.openable.isOpen();
    }

    @Override
    public void open() {
        if (!this.isLocked) {
            this.openable.open();
        }
    }

    @Override
    public Boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public Boolean canUnlock(Component component) {
        return this.key.equals(component);
    }

    @Override
    public Boolean unlock(Component component) {
        if (this.canUnlock(component)) {
            this.isLocked = Boolean.FALSE;
        }
        return this.isLocked;
    }
}
