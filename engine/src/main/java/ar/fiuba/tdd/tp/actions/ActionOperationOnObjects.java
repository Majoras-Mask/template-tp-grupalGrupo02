package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public abstract class ActionOperationOnObjects implements Action {

    private Value objectDescription;
    private Value objectToAddDescription;
    private Context context;

    public ActionOperationOnObjects(Value objectDescription, Value objectToAddDescription, Context context) {
        this.objectDescription = objectDescription;
        this.objectToAddDescription = objectToAddDescription;
        this.context = context;
    }

    @Override
    public void execute() {
        ObjectInterface primaryObject = context.getObject(objectDescription.getValue());
        ObjectInterface secondaryObject = context.getObject(objectToAddDescription.getValue());

        if (primaryObject != null && secondaryObject != null) {
            doOperationOnObjects(primaryObject, secondaryObject);
        }
    }

    public abstract void doOperationOnObjects(ObjectInterface primaryObject, ObjectInterface secondaryObject);

}
