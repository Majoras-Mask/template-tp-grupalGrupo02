package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.ActionType;
import ar.fiuba.tdd.tp.game.player.action.io.ActionRequest;
import ar.fiuba.tdd.tp.game.player.action.io.ActionResponse;

import java.util.List;

/**
 * Represents some state that may have a {@link Component}.
 * A state can be altered by some {@link Action}, for example: a door can change it's
 * state (from close to open) when the Action Open is executed
 */
public interface ComponentState {

    /*
     * Returns all actions that modifies this state
     */
    List<ActionType> getTriggerActions();

    /*
     * Perform the action
     */
    ActionResponse execute(ActionRequest request);

}
