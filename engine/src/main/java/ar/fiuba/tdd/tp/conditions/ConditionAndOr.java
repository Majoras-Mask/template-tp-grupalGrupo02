package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 28/05/16.
 */
public abstract class ConditionAndOr extends ConditionLogic {

    protected Condition condition1;
    protected Condition condition2;

    public ConditionAndOr(Condition condition1, Condition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;

    }
}
