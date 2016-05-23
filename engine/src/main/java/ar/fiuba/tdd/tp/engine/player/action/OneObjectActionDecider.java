package ar.fiuba.tdd.tp.engine.player.action;

import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.player.Player;
import ar.fiuba.tdd.tp.engine.player.action.resolver.ActionDeciderAbstract;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Many actions require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" actions, and the object that a transitive action
 * operates upon is called the action's 'direct object.'
 */
public class OneObjectActionDecider extends ActionDeciderAbstract {

    private final Player player;

    public OneObjectActionDecider(String commandName, Constraint constraint, Player player) {
        super(commandName, constraint);
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
        return actionConstraint.isSatisfied();
    }
}
