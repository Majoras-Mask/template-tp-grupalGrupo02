package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.state.ComponentState;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ComponentImpl implements Component {

    private final String name;
    private final List<ComponentState> states;
    private List<Constraint> constraints;

    public ComponentImpl(String name, List<ComponentState> states) {
        this.name = name;
        this.states = states;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ActionType> getSupportedActions() {
        return this.states.stream().map(ComponentState::getTriggerActions)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    @Override
    public Boolean supports(ActionType actionType) {
        return this.getSupportedActions().contains(actionType);
    }

    @Override
    public Boolean satisfiesConstraints() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ActionResponse doAction(ActionRequest request) {
        List<ComponentState> collect = this.states.stream()
                .filter(state -> state.getTriggerActions().contains(request.getType()))
                .collect(toList());

        if (collect.size() != 1) {
            throw new IllegalStateException("It's expected one state to handle the action " + request.getType());
        }

        return collect.get(0).execute(request);
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }
}
