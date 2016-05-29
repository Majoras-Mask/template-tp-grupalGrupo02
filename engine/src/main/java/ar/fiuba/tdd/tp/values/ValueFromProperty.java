package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;

/**
 * Created by kevin on 28/05/16.
 */
public class ValueFromProperty implements Value {

    private Value objectDescription;
    private Value propertyDescription;

    public ValueFromProperty(Value objectDescription, Value propertyDescription) {
        this.objectDescription = objectDescription;
        this.propertyDescription = propertyDescription;
    }

    @Override
    public String getValue(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        return object.getProperty(propertyDescription.getValue(context));
    }
}
