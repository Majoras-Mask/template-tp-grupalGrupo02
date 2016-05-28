package ar.fiuba.tdd.tp.conditionelements;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;

/**
 * Created by kevin on 28/05/16.
 */
public interface ConditionElement extends Element {
    void setCondition(Condition condition);

    void addElementsToExecute(Element element);
}
