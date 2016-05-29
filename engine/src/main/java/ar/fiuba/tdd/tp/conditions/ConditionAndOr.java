package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionAndOr implements Condition {

    private Condition condition1;
    private Condition condition2;
    private boolean andOperation;

    public ConditionAndOr(boolean andOperation, Condition condition1, Condition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
        this.andOperation = andOperation;

    }

    @Override
    public boolean check(Context context) {
        if (andOperation) {
            return condition1.check(context) && condition2.check(context);
        }
        return condition1.check(context) || condition2.check(context);
    }
}
