package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.player.action.ActionDecider;
import ar.fiuba.tdd.tp.game.player.action.impl.FallBackAction;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Finds the {@link ActionDecider} that can handle the input command.
 * In case that no behavior can handles that command, a fallback response
 * is retrieved.
 */
public class ActionResolver {

    private static final ActionDecider FALL_BACK_ACTION_DECIDER = new FallBackAction();

    private final Set<ActionDecider> actionDeciders;

    public ActionResolver(Set<ActionDecider> actionDeciders) {
        this.actionDeciders = actionDeciders;
    }

    public String execute(String command) {
        final ActionDecider actionDecider = this.findAction(command);
        //TODO implement constrains for action esto ya esta en las acciones(OneObjectAction), hay que moverlo aca?
//        List<Constraint> constrains = action.getConstrains();
        return actionDecider.execute(command);
    }

    private ActionDecider findAction(String command) {
        final List<ActionDecider> filter = this.actionDeciders.stream()
                .filter(action -> action.canExecute(command))
                .collect(toList());

        if (filter.size() > 1) {
            throw new IllegalStateException("More that one behavior supports the command: " + command);
        }

        return (filter.size() == 0 ) ? FALL_BACK_ACTION_DECIDER : filter.get(0);
    }

}