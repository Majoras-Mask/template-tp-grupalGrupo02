package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;

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
