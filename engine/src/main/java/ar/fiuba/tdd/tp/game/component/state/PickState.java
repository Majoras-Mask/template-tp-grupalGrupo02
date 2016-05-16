package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static ar.fiuba.tdd.tp.game.player.action.ActionType.DROP;
import static ar.fiuba.tdd.tp.game.player.action.ActionType.PICK;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PickState extends BooleanState {

    public PickState(Boolean isPicked, Map<ActionType, Constraint> constraints) {
        super(isPicked, constraints);
    }

    public Boolean isPicked() {
        return this.getValue();
    }

    public ActionResponse pick(ActionRequest request) {
        return this.returnResponse(TRUE, "picked!");
    }

    public ActionResponse drop(ActionRequest request) {
        return this.returnResponse(FALSE, "dropped!");
    }

    private ActionResponse returnResponse(Boolean value, String message) {
        this.setValue(value);
        return new ActionResponse(TRUE, message);
    }

    @Override
    protected Map<ActionType, Function<ActionRequest, ActionResponse>> buildActionMap() {
        final Map<ActionType, Function<ActionRequest, ActionResponse>> response;
        response = new HashMap<>();

        response.put(PICK, this::pick);
        response.put(DROP, this::drop);

        return response;
    }
}
