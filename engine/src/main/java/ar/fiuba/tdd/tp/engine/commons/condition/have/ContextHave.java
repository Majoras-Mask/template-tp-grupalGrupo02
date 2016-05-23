package ar.fiuba.tdd.tp.engine.commons.condition.have;

import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.scenario.context.Context;

/**
 * True if a {@link Context} has (or not) some {@link Component}.
 */
public class ContextHave extends HaveCondition {

    private final Context context;
    private final String name;

    public ContextHave(Context context, String name, HaveType type) {
        super(type);
        this.name = name;
        this.context = context;
    }

    protected Boolean isPresent() {
        return this.context.findComponentByName(name).isPresent();
    }

}
