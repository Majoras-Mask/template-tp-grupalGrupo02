package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.ActionDecider;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;

import java.util.List;
import java.util.regex.Pattern;

public abstract class ActionDeciderAbstract implements ActionDecider {
    protected List<Constraint> constraints;
    protected Pattern commandPattern;
    protected List<Action> actions;
    protected String commandName;

    public ActionDeciderAbstract(String commandName, List<Action> actions, List<Constraint> constraints) {
        this.commandName = commandName;
        this.actions = actions;
        this.constraints = constraints;
    }

    public Boolean satisfiesActionConstraints() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }
}
