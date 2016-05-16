package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Many actions require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" actions, and the object that a transitive action
 * operates upon is called the action's 'direct object.'
 */
public abstract class OneObjectAction implements Action {

    protected final Player player;
    private final Pattern commandPattern;
    private final String pattern;

    protected OneObjectAction(Player player, String pattern) {
        this.player = player;
        this.pattern = pattern;
        this.commandPattern = Pattern.compile(pattern);
    }

    @Override
    public String execute(String action) {
        final String directObject = action.replaceFirst(pattern, "");
        final Optional<Component> component = this.getDirectObject(directObject);

        if (!component.isPresent()) {
            return "There is no " + directObject + " in the " + this.player.getCurrentContext().getName();
        }

        return this.doExecute(component.get());
    }

    private Optional<Component> getDirectObject(String directObject) {
        return this.player.getCurrentContext().findComponentByName(directObject);
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }

    protected abstract String doExecute(Component component);

}
