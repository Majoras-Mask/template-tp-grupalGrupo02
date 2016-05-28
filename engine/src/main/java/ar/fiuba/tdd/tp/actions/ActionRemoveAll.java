package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public class ActionRemoveAll implements Action {

    Value objectDescription;
    Context context;

    public ActionRemoveAll(Value objectDescription, Context context) {
        this.objectDescription = objectDescription;
        this.context = context;
    }

    @Override
    public void execute() {
        ObjectInterface objectInterface = context.getObject(objectDescription.getValue());
        if (objectInterface != null) {
            objectInterface.removeAll();
        }
    }
}
