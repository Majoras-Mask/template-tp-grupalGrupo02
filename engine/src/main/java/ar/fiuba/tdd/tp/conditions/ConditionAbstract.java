package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public abstract class ConditionAbstract implements Condition {
    protected Value objectDescription;
    protected Context context;

    public ConditionAbstract(Value objectDescription, Context context) {
        this.objectDescription = objectDescription;
        this.context = context;
    }
}
