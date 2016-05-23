package ar.fiuba.tdd.tp.engine.component;

import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.player.action.impl.Action;

import java.util.List;
import java.util.Set;

/**
 * Interface for all the components that a Game needs to work.
 * */
public interface Component {

    String getName();

    Set<String> getSupportedActions();

    void addAction(String actionName, List<Action> actionsList, Constraint constraint);

    Boolean satisfiesConstraints(String action);

    String doAction(String commandName);
}
