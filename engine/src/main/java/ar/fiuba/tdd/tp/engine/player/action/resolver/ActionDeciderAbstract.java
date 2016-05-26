package ar.fiuba.tdd.tp.engine.player.action.resolver;

import ar.fiuba.tdd.tp.engine.commons.condition.Condition;
import ar.fiuba.tdd.tp.engine.commons.condition.have.NoConditionRequired;
import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.commons.constraint.ConstraintImpl;
import ar.fiuba.tdd.tp.engine.player.action.ActionDecider;

import java.util.ArrayList;
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

    public ActionDeciderAbstract(String commandName) {
        Constraint constraint;
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new NoConditionRequired());
        constraint = new ConstraintImpl(conditions);

        this.commandName = commandName;
        this.actionConstraint = constraint;
    }

    public Boolean satisfiesActionConstraints() {
        return actionConstraint.isSatisfied();
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }
}
