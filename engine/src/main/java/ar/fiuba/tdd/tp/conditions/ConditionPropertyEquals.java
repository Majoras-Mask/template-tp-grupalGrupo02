package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionPropertyEquals extends ConditionAbstract {

    private Value valueProperty;
    private Value property;

    public ConditionPropertyEquals(Value objectDescription, Value property, Value valueProperty) {
        super(objectDescription);
        this.property = property;
        this.valueProperty = valueProperty;
    }

    @Override
    public boolean check(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        String actualPropertyValue = object.getProperty(property.getValue(context));

        return (actualPropertyValue.equals(valueProperty.getValue(context)));
    }

}
