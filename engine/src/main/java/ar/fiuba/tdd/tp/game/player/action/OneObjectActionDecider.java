package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionDeciderAbstract;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Many actions require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" actions, and the object that a transitive action
 * operates upon is called the action's 'direct object.'
 */
public abstract class OneObjectActionDecider extends ActionDeciderAbstract {

    private final Player player;

    public OneObjectActionDecider(String commandName, List<Constraint> constraints, Player player) {
        super(commandName, constraints);
        this.commandPattern = Pattern.compile("^" + commandName + " ");
        this.player = player;
    }


    @Override
    public String execute(String action) {
        final String directObject = action.replaceFirst(commandName, "");
        final Optional<Component> component = this.getDirectObject(directObject);

        if (!component.isPresent()) {
            return "There is no " + directObject + " in the " + this.player.getCurrentContext().getName();
        }

        if (satisfiesActionConstraints() && satisfiesItemConstraints(component.get())) {
            return component.get().doAction(commandName);
        }
        return "No se cumple un constraint de la accion o del objeto.";
    }

    private boolean satisfiesItemConstraints(Component component) {
        return component.satisfiesConstraints(commandName);
    }

    private Optional<Component> getDirectObject(String directObject) {
        return this.player.getCurrentContext().findComponentByName(directObject);
    }

    public Boolean satisfiesActionConstraints() {
        for (Constraint constraint : actionConstraints) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }
}
