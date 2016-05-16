package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LockState extends ComponentStateImpl {

    private Boolean isLock;
    private Component key;

    public LockState(Component key, Boolean isLock, Map<ActionType, Constraint> constraints) {
        super(constraints);
        this.isLock = isLock;
        this.key = key;
    }

    public Boolean isLocked() {
        return this.isLock;
    }

    public Boolean canUnlock(Component unlocker) {
        return this.key.equals(unlocker);
    }

    public ActionResponse unlock(ActionRequest request) {
        return this.doAction(request, "Unlocked!");
    }

    public ActionResponse lock(ActionRequest request) {
        this.isLock = request.getArguments().stream().anyMatch(this::canUnlock);
        return new ActionResponse(this.isLock, (this.isLock) ? "Wrong key" : "Locked!");
    }

    /*
     * Lock or Unlock works in the same way
     */
    private ActionResponse doAction(ActionRequest request, String successMessage) {
        this.isLock = request.getArguments().stream().anyMatch(this::canUnlock);
        return new ActionResponse(this.isLock, (this.isLock) ? "Wrong key" : successMessage);
    }

    @Override
    protected Map<ActionType, Function<ActionRequest, ActionResponse>> buildActionMap() {
        final Map<ActionType, Function<ActionRequest, ActionResponse>> response;
        response = new HashMap<>();
        response.put(ActionType.LOCK, this::lock);
        response.put(ActionType.UNLOCK, this::unlock);
        return response;
    }

}
