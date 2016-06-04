package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 04/06/16.
 */
public class ConditionOr extends ConditionAndOr {
    public ConditionOr(Condition condition1, Condition condition2) {
        super(condition1, condition2);
    }

    @Override
    public boolean check(Context context) {
        return condition1.check(context) || condition2.check(context);
    }
}
