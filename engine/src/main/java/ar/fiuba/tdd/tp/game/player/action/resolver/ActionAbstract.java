package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.Action;

import java.util.List;
import java.util.regex.Pattern;

public abstract class ActionAbstract implements Action {
    protected List<Constraint> constraints;
    protected Player player;
    protected Pattern commandPattern;

    public ActionAbstract(Player player, String pattern) {
        this.player = player;
        this.commandPattern = Pattern.compile(pattern);
    }

    public Boolean satisfiesActionConstraints() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }
}
