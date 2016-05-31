package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionAlwaysTrue implements Condition {

    @Override
    public boolean check(Context context) {
        return true;
    }
}
