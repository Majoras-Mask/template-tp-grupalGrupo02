package ar.fiuba.tdd.tp.engine.player.action;

import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.player.action.impl.Action;
import ar.fiuba.tdd.tp.engine.player.action.resolver.ActionDeciderAbstract;

import java.util.List;
import java.util.regex.Pattern;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public class NoObjectActionDecider extends ActionDeciderAbstract {

    private final List<Action> actions;

    public NoObjectActionDecider(String commandName, List<Action> actions, Constraint constraint) {
        super(commandName, constraint);
        this.commandPattern = Pattern.compile("^" + commandName + "$");
        this.actions = actions;
    }

    public NoObjectActionDecider(String commandName, List<Action> actions) {
        super(commandName);
        this.commandPattern = Pattern.compile("^" + commandName + "$");
        this.actions = actions;
    }

    @Override
    public String execute(String fullMessage) {
        if (satisfiesActionConstraints()) {
            StringBuffer message = new StringBuffer();
            for (Action action : actions) {
                message.append(action.doAction()).append("\n");
            }
            return message.toString();
        }
        return "No cumple una condicion";
    }
}
