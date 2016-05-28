package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;

/**
 * Created by kevin on 28/05/16.
 */
public class ValueFromProperty implements Value {

    private Value objectDescription;
    private Value propertyDescription;
    private Context context;

    public ValueFromProperty(Value objectDescription, Value propertyDescription, Context context) {
        this.context = context;
        this.objectDescription = objectDescription;
        this.propertyDescription = propertyDescription;
    }

    @Override
    public String getValue() {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        return object.getProperty(propertyDescription.getValue());
    }
}
