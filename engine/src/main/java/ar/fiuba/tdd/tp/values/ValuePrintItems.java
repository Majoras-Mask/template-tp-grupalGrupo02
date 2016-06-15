package ar.fiuba.tdd.tp.values;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.ObjectInterface;

import java.util.List;

/**
 * Created by kevin on 15/06/16.
 */
public class ValuePrintItems implements Value {

    private Value objectDescription;

    public ValuePrintItems(Value objectDescription) {
        this.objectDescription = objectDescription;
    }

    private String getAllItemsMessage(ObjectInterface objectInterface) {
        List<ObjectInterface> objects = objectInterface.getObjects();

        if (objects.isEmpty()) {
            return "Empty.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < objects.size() - 1; i++) {
            stringBuilder.append(objects.get(i).getDescription());
            stringBuilder.append(" , ");
        }
        stringBuilder.append(objects.get(objectInterface.getSize() - 1).getDescription());
        stringBuilder.append(".");

        return stringBuilder.toString();
    }

    @Override
    public String getValue(Context context) {
        ObjectInterface object = context.getObject(objectDescription.getValue(context));
        return getAllItemsMessage(object);
    }
}
