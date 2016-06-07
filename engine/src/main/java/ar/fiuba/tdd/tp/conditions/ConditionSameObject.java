package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 06/06/16.
 */
public class ConditionSameObject extends ConditionLogic {

    private Value value;
    private Value other;

    public ConditionSameObject(Value value, Value other) {
        this.value = value;
        this.other = other;
    }

    @Override
    public boolean check(Context context) {
        return context.getObject(value.getValue(context)) == context.getObject(other.getValue(context));
    }
}
