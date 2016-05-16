package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

abstract class ComponentStateImpl implements ComponentState {

    protected final Map<ActionType, Constraint> constraints;
    protected final Map<ActionType, Function<ActionRequest, ActionResponse>> actionMap;

    protected ComponentStateImpl(Map<ActionType, Constraint> constraints) {
        this.constraints = constraints;
        this.actionMap = buildActionMap();
    }

    @Override
    public ActionResponse execute(ActionRequest request) {
        ComponentAction componentAction = this.getComponentAction(request);
        return componentAction.execute(request);
    }

    private ComponentAction getComponentAction(ActionRequest request) {
        return new ComponentAction(this.constraints.get(request.getType()), this.actionMap.get(request.getType()));
    }

    @Override
    public List<ActionType> getTriggerActions() {
        return this.actionMap.keySet().stream().collect(toList());
    }

    protected abstract Map<ActionType, Function<ActionRequest, ActionResponse>> buildActionMap();
}
