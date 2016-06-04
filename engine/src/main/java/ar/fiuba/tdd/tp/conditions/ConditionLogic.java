package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 04/06/16.
 */
public abstract class ConditionLogic implements Condition {

    @Override
    public Condition and(Condition other) {
        return new ConditionAnd(this, other);
    }

    @Override
    public Condition or(Condition other) {
        return new ConditionOr(this, other);
    }

    @Override
    public Condition not() {
        return new ConditionNOT(this);
    }
}
