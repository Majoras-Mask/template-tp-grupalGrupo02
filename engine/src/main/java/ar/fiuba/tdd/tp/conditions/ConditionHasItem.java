package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionHasItem extends ConditionAbstract {

    private Value item;

    public ConditionHasItem(Value objectDescription, Value item) {
        super(objectDescription);
        this.item = item;
    }

    @Override
    public boolean check(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        ObjectInterface objectItem = context.getObject(item.getValue());
        return object.hasObject(objectItem);
    }

}
