/*package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.TwoObjectAction;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;

*//*
 * This behavior executes the open operation on the given component
 *//*
public class Lock extends TwoObjectAction {

    public Lock(Context context) {
        super(context, "^lock .*");
    }

    @Override
    protected String execute(Component directComponent, Component indirectComponent) {
        Boolean supports = directComponent.supports(ActionType.LOCK);

        //TODO Do this..
        if (supports) {
            List<Component> arguments = new ArrayList<>();
            arguments.add(indirectComponent);
            directComponent.doAction(new ActionRequest(ActionType.LOCK, arguments));
        }

        return null;
    }

}*/
