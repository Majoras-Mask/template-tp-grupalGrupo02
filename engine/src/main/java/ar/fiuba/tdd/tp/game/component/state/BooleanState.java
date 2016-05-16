package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.ActionType;

import java.util.Map;

public abstract class BooleanState extends ComponentStateImpl {

    protected Boolean value;

    protected BooleanState(Boolean value, Map<ActionType, Constraint> constraints) {
        super(constraints);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
