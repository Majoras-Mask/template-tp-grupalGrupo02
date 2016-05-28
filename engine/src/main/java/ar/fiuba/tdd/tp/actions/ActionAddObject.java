package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionAddObject extends ActionOperationOnObjects {

    public ActionAddObject(Value objectDescription, Value objectToAddDescription, Context context) {
        super(objectDescription, objectToAddDescription, context);
    }

    @Override
    public void doOperationOnObjects(ObjectInterface primaryObject, ObjectInterface secondaryObject) {
        primaryObject.add(secondaryObject);
    }

}
