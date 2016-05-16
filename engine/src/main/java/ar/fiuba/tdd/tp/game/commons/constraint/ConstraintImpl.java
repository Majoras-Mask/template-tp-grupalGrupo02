package ar.fiuba.tdd.tp.game.commons.constraint;

import ar.fiuba.tdd.tp.game.commons.condition.Condition;

import java.util.List;

public class ConstraintImpl implements Constraint {

    private final List<Condition> conditions;

    public ConstraintImpl(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public Boolean isSatisfied() {
        return this.conditions.stream().allMatch(Condition::accomplished);
    }
}
