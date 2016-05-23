package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.List;
import java.util.Set;

/**
 * Interface for all the components that a Game needs to work.
 * Every component has a list of {@link ActionType} that supports.
 */
public interface Component {

    String getName();

    Set<String> getSupportedActions();

    Boolean supports(String actionType);

    Boolean satisfiesConstraints(String action);
}
