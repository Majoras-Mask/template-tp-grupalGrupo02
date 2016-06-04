package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionNOT extends ConditionLogic {

    private Condition condition;

    public ConditionNOT(Condition condition) {
        this.condition = condition;
    }
    
    @Override
    public boolean check(Context context) {
        return !condition.check(context);
    }
}
