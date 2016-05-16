package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static ar.fiuba.tdd.tp.game.player.action.ActionType.CLOSE;
import static ar.fiuba.tdd.tp.game.player.action.ActionType.OPEN;

public class OpenState extends ComponentStateImpl {

    private Boolean isOpen;

    public OpenState(Boolean isOpen, Map<ActionType, Constraint> constraints) {
        super(constraints);
        this.isOpen = isOpen;
    }

    public Boolean isOpen() {
        return this.isOpen;
    }

    public ActionResponse open(ActionRequest request) {
        this.isOpen = Boolean.TRUE;
        return new ActionResponse(Boolean.TRUE, "opened");
    }

    public ActionResponse close(ActionRequest request) {
        this.isOpen = Boolean.FALSE;
        return new ActionResponse(Boolean.TRUE, "closed");
    }

    @Override
    protected Map<ActionType, Function<ActionRequest, ActionResponse>> buildActionMap() {
        final HashMap<ActionType, Function<ActionRequest, ActionResponse>> response = new HashMap<>();
        response.put(OPEN, this::open);
        response.put(CLOSE, this::close);
        return response;
    }

}
