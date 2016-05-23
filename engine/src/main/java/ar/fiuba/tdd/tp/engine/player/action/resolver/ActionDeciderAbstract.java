package ar.fiuba.tdd.tp.engine.player.action.resolver;

import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.player.action.ActionDecider;

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
