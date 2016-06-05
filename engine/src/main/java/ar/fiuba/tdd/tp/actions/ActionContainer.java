package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 29/05/16.
 */
public class ActionContainer implements Action {

    protected List<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        if (!actions.contains(action)) {
            actions.add(action);
        }
    }

    @Override
    public void execute(Context context) {
        for (Action action:actions) {
            action.execute(context);
        }
    }
}
