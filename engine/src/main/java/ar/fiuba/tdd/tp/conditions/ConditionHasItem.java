package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionHasItem implements Condition {

    private Value objectDescription;
    private Value item;
    private Context context;

    public ConditionHasItem(Value objectDescription, Value item, Context context) {
        this.objectDescription = objectDescription;
        this.item = item;
        this.context = context;
    }

    @Override
    public boolean check() {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        ObjectInterface objectItem = context.getObject(item.getValue());
        return object.hasObject(objectItem);
    }

}
