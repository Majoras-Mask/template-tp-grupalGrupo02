package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionDecider;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;

import java.util.List;
import java.util.regex.Pattern;

public abstract class ActionDeciderAbstract implements ActionDecider {
    protected List<Constraint> actionConstraints;
    protected Pattern commandPattern;
    protected String commandName;

    public ActionDeciderAbstract(String commandName,List<Constraint> actionConstraints) {
        this.commandName = commandName;
        this.actionConstraints = actionConstraints;
    }

    public Boolean satisfiesActionConstraints() {
        for (Constraint constraint : actionConstraints) {
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
