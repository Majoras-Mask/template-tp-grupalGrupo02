package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Logger;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.values.Value;

import java.util.List;

/**
 * Created by kevin on 29/05/16.
 */
public class ActionLookAround implements Action {

    private Value objectDescription;
    private Logger logger;

    public ActionLookAround(Value objectDescription, Logger logger) {
        this.objectDescription = objectDescription;
        this.logger = logger;
    }

    private ObjectInterface getObject(Context context, Value value) {
        return context.getObject(value.getValue(context));
    }

    @Override
    public void execute(Context context) {
        ObjectInterface object = getObject(context, objectDescription);
        StringBuilder stringBuilder = new StringBuilder("There's ");
        List<ObjectInterface> objects = object.getObjects();
        for ( ObjectInterface obj : objects ) {
            stringBuilder.append(obj.getDescription());
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length());
        stringBuilder.append(" in ");
        stringBuilder.append(object.getDescription());
        logger.log(stringBuilder.toString());
    }
}
