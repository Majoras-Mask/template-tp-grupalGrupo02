package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.ObjectNull;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 29/05/16.
 */
public class ConditionGameHasObject extends ConditionLogic {

    private Value objectDescription;

    public ConditionGameHasObject(Value objectDescription) {
        this.objectDescription = objectDescription;
    }

    @Override
    public boolean check(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        return object != ObjectNull.getInstance();
    }
}
