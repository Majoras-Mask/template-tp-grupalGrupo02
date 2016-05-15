package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.context.GameContext;

import java.util.regex.Pattern;

public abstract class NoObjectActionImpl implements NoObjectAction {

    protected final GameContext context;
    private final Pattern commandPattern;

    protected NoObjectActionImpl(GameContext context, String pattern) {
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

}
