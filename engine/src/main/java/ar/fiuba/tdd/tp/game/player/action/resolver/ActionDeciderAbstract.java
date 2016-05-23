package ar.fiuba.tdd.tp.game.player.action.resolver;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionDecider;
import ar.fiuba.tdd.tp.game.player.action.impl.Action;

import java.util.List;
import java.util.regex.Pattern;

public abstract class ActionDeciderAbstract implements ActionDecider {
    protected Constraint actionConstraint;
    protected Pattern commandPattern;
    protected String commandName;

    public ActionDeciderAbstract(String commandName,Constraint actionConstraint) {
        this.commandName = commandName;
        this.actionConstraint = actionConstraint;
    }

    public Boolean satisfiesActionConstraints() {
        return actionConstraint.isSatisfied();
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }
}
