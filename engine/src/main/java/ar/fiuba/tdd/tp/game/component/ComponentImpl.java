package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.state.ComponentState;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComponentImpl implements Component {

    private final String name;
    private final List<ComponentState> states;
    private Map<String,List<Constraint>> actions;

    public ComponentImpl(String name, List<ComponentState> states, Map<String,List<Constraint>> actions) {
        this.name = name;
        this.states = states;
        this.actions = actions;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<String> getSupportedActions() {
        return actions.keySet();
    }

    @Override
    public Boolean supports(String actionType) {
        return this.getSupportedActions().contains(actionType);
    }

    @Override
    public Boolean satisfiesConstraints(String action) {
        for (Constraint constraint : actions.get(action)) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }
}
