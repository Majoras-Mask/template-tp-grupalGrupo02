package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionDeciderAbstract;

import java.util.List;
import java.util.regex.Pattern;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public abstract class NoObjectActionDecider extends ActionDeciderAbstract {


    public NoObjectActionDecider(String commandName, List<Action> actions, List<Constraint> constraints) {
        super(commandName, actions, constraints);
        this.commandPattern = Pattern.compile("^" + commandName + "$");
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
