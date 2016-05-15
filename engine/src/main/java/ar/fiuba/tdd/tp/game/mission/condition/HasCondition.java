package ar.fiuba.tdd.tp.game.mission.condition;

/**
 * Returns true when:
 * -If {@link ConditionType} is HAVE, and the isPresent return true
 * -If {@link ConditionType} is NOT_HAVE, and the isPresent returns false
 * In any other case, it returns false. (It's just a NXOR between type and isPresent)
 */
abstract class HasCondition implements Condition {

    private final ConditionType type;

    HasCondition(ConditionType type) {
        this.type = type;
    }

    @Override
    public Boolean accomplished() {
        return this.convertType().equals(this.isPresent());
    }

    private Boolean convertType() {
        return (ConditionType.HAVE.equals(type)) ? Boolean.TRUE : Boolean.FALSE;
    }

    protected abstract Boolean isPresent();
}
