package ar.fiuba.tdd.tp.engine.commons.condition.have;

import ar.fiuba.tdd.tp.engine.commons.condition.Condition;

/**
 * Returns true when:
 * -If {@link HaveType} is HAVE, and the isPresent return true
 * -If {@link HaveType} is NOT_HAVE, and the isPresent returns false
 * In any other case, it returns false. (It's just a NXOR between type and isPresent)
 */
abstract class HaveCondition implements Condition {

    private final HaveType type;

    HaveCondition(HaveType type) {
        this.type = type;
    }

    @Override
    public Boolean accomplished() {
        return this.type.getBoolValue().equals(this.isPresent());
    }

    protected abstract Boolean isPresent();
}
