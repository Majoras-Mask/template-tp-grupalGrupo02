package ar.fiuba.tdd.tp.game.commons.condition.have;

public enum HaveType {
    HAVE(Boolean.TRUE), NOT_HAVE(Boolean.FALSE);

    private final Boolean boolValue;

    HaveType(Boolean boolValue) {
        this.boolValue = boolValue;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }
}
