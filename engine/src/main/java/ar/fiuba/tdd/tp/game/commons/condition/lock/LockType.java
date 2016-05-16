package ar.fiuba.tdd.tp.game.commons.condition.lock;

public enum LockType {
    LOCK(Boolean.TRUE), UNLOCK(Boolean.FALSE);

    private final Boolean value;

    LockType(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }
}
