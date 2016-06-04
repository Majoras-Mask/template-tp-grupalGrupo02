package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */


public class ConditionSize extends ConditionLogic {

    private ConditionSizeOperation operation;
    protected Value objectDescription;
    protected int argument;

    public ConditionSize(Value objectDescription, int argument, ConditionSizeOperation operation) {
        this.objectDescription = objectDescription;
        this.argument = argument;
        this.operation = operation;
    }

    @Override
    public boolean check(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        return operation.compare(object.getSize(), argument);
    }
}
