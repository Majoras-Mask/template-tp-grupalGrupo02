package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.game.player.action.ActionType.OPEN;

/*
 * This behavior executes the open operation on the given component
 */
public class Open extends OneObjectAction {

    public Open(Context context) {
        super(context, "^open ");
    }

    @Override
    public String doExecute(Component component) {
        if (!component.supports(OPEN)) {
            return component.getName() + " can not be open!";
        }

        return doOpen(component);
    }

    private String doOpen(Component component) {
        ActionResponse actionResponse = component.doAction(new ActionRequest(OPEN));
        if (!actionResponse.success()) {
            return "This " + component.getName() + " can not be open: " + actionResponse.getMessage();
        }

        return "Opened!";
    }

    @Override
    public List<Constraint> getConstrains() {
        return new ArrayList<>();
    }
}
