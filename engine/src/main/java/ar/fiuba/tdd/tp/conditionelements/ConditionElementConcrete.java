package ar.fiuba.tdd.tp.conditionelements;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionElementConcrete implements ConditionElement {

    private Condition condition;
    private List<Element> elements = new ArrayList<>();


    @Override
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void addElementsToExecute(Element element) {
        this.elements.add(element);
    }

    @Override
    public void execute(Context context) {
        if (condition.check(context)) {
            for (Element e: elements) {
                e.execute(context);
            }
        }
    }
}
