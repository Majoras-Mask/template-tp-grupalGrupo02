package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public abstract class ConditionAbstract extends ConditionLogic {
    protected Value objectDescription;

    public ConditionAbstract(Value objectDescription) {
        this.objectDescription = objectDescription;
    }
}
