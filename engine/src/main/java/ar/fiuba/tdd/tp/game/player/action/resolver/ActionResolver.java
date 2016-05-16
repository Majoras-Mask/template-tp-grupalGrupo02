package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.FallBackAction;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Finds the {@link Action} that can handle the input command.
 * In case that no behavior can handles that command, a fallback response
 * is retrieved.
 */
public class ActionResolver {

    private static final Action FALL_BACK_ACTION = new FallBackAction();

    private final Set<Action> actions;

    public ActionResolver(Set<Action> actions) {
        this.actions = actions;
    }

    public String execute(String command) {
        final Action action = this.findAction(command);
        //TODO implement constrains for action
//        List<Constraint> constrains = action.getConstrains();
        return action.execute(command);
    }

    private Action findAction(String command) {
        final List<Action> filter = this.actions.stream()
                .filter(action -> action.canExecute(command))
                .collect(toList());

        if (filter.size() > 1) {
            throw new IllegalStateException("More that one behavior supports the command: " + command);
        }

        return (filter.size() == 0 ) ? FALL_BACK_ACTION : filter.get(0);
    }

}