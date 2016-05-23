package ar.fiuba.tdd.tp.engine.commons.condition.have;

import ar.fiuba.tdd.tp.engine.commons.condition.Condition;

public class NoConditionRequired implements Condition {
    @Override
    public Boolean accomplished() {
        return true;
    }
}
