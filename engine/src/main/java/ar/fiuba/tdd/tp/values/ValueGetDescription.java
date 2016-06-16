package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;

/**
 * Created by kevin on 15/06/16.
 */
public class ValueGetDescription implements Value {

    private Value objectDescription;

    public ValueGetDescription(Value objectDescription) {
        this.objectDescription = objectDescription;
    }

    @Override
    public String getValue(Context context) {
        ObjectInterface objectInterface = context.getObject(objectDescription.getValue(context));
        return objectInterface.getDescription();
    }
}
