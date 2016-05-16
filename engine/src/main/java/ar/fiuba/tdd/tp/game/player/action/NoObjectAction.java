package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.regex.Pattern;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public abstract class NoObjectAction implements Action {

    protected final Player player;
    private final Pattern commandPattern;

    protected NoObjectAction(Player player, String pattern) {
        this.player = player;
        this.commandPattern = Pattern.compile(pattern);
    }

    @Override
    public String execute(String action) {
        return this.doExecute();
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }

    protected abstract String doExecute();

}
