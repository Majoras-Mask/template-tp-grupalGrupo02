package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionSetProperty implements  Action {

    private Value property;
    private Value value;
    private Value objectDescription;
    private Context context;

    public ActionSetProperty(Value property, Value value, Value objectDescription, Context context) {
        this.property = property;
        this.value = value;
        this.objectDescription = objectDescription;
        this.context = context;
    }


    @Override
    public void execute() {
        ObjectInterface object = context.getObject(objectDescription.getValue());
        String stringProperty = property.getValue();
        String stringValue = value.getValue();

        if (object != null) {
            object.setProperty(stringProperty, stringValue);
        }
    }
}
