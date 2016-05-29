package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionRemoveObject extends ActionOperationOnObjects {

    public ActionRemoveObject(Value objectDescription, Value objectToAddDescription) {
        super(objectDescription, objectToAddDescription);
    }

    @Override
    public void doOperationOnObjects(ObjectInterface primaryObject, ObjectInterface secondaryObject) {
        primaryObject.remove(secondaryObject);
    }

}
