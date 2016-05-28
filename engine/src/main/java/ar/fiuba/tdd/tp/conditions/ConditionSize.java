package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */


public class ConditionSize implements Condition {

    private ConditionSizeOperation operation;
    protected Value objectDescription;
    protected int argument;
    protected Context context;

    public ConditionSize(Value objectDescription, int argument, ConditionSizeOperation operation, Context context) {
        this.objectDescription = objectDescription;
        this.argument = argument;
        this.context = context;
        this.operation = operation;
    }

    @Override
    public boolean check() {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        return operation.compare(object.getSize(), argument);
    }
}
