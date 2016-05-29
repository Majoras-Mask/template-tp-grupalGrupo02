package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionRemoveAll implements Action {

    Value objectDescription;

    public ActionRemoveAll(Value objectDescription) {
        this.objectDescription = objectDescription;
    }

    @Override
    public void execute(Context context) {
        ObjectInterface objectInterface = context.getObject(objectDescription.getValue(context));
        if (objectInterface != null) {
            objectInterface.removeAll();
        }
    }
}
