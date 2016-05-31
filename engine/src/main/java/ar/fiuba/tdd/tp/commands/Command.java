package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;

/**
 * Created by kevin on 28/05/16.
 */
public interface Command {

    boolean matches(String command, Context context);

    String execute(String command, Context context);

    void setCondition(Condition condition);

    void addElement(Element element);
}
