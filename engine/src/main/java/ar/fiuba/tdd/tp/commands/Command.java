package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.values.Value;

/**
 * Created by kevin on 28/05/16.
 */
public interface Command {

    boolean matches(String command);

    String execute(String command, Context context);

    void setCondition(Condition condition, Element toExecute, String response);

    void setCondition(Condition condition, Element toExecute, Value response);
}
