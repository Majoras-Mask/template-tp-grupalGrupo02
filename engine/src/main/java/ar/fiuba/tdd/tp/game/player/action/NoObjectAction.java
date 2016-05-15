package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.regex.Pattern;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public abstract class NoObjectAction implements Action {

    protected final Context context;
    private final Pattern commandPattern;

    protected NoObjectAction(Context context, String pattern) {
        this.context = context;
        this.commandPattern = Pattern.compile(pattern);
    }

    @Override
    public String doExecute(String action) {
        return this.execute();
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }

    protected abstract String execute();

}
