package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.function.Function;

import static java.util.Objects.isNull;


/*
 * This class groups a {@link Constraint} and a function to doExecute
 * (in case that the constraint is not violated)
 */
public class ComponentAction {

    private final Constraint constraint;
    private final Function<ActionRequest, ActionResponse> function;

    public ComponentAction(Constraint constraints, Function<ActionRequest, ActionResponse> function) {
        this.constraint = constraints;
        this.function = function;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public Boolean canBeExecuted() {
        return (isNull(this.constraint)) ? Boolean.TRUE : this.constraint.isSatisfied();
    }

    public ActionResponse execute(ActionRequest request) {
        if (!this.canBeExecuted()) {
            return new ActionResponse(Boolean.FALSE, "This action can't be executed!");
        }
        return function.apply(request);
    }
}
