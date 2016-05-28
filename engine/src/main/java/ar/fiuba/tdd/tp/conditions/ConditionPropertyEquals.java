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

    public ConditionPropertyEquals(Value objectDescription, Value property, Value valueProperty, Context context) {
        super(objectDescription, context);
        this.property = property;
        this.valueProperty = valueProperty;
    }

    @Override
    public boolean check() {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        String actualPropertyValue = object.getProperty(property.getValue());

        return (actualPropertyValue.equals(valueProperty.getValue()));
    }

}
