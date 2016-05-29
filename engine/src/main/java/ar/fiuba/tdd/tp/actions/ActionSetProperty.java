package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionSetProperty implements Action {

    private Value property;
    private Value value;
    private Value objectDescription;

    public ActionSetProperty(Value objectDescription, Value property, Value value, Context context) {
        this.property = property;
        this.value = value;
        this.objectDescription = objectDescription;
    }


    @Override
    public void execute(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        String stringProperty = property.getValue(context);
        String stringValue = value.getValue(context);

        if (object != null) {
            object.setProperty(stringProperty, stringValue);
        }
    }
}
