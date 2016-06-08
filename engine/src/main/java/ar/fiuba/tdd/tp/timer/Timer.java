package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.conditions.Condition;

/**
 * Created by kevin on 29/05/16.
 */
public interface Timer {

    void update(Context context, Sender sender);

    void setCondition(Condition condition, Element toExecute, String response);

    boolean isFinished();
}
