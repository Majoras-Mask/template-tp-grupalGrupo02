package ar.fiuba.tdd.tp.game.mission.condition;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

/**
 * True if a {@link Context} has (or not) some {@link Component}.
 */
public class ScenarioHas extends HasCondition {

    private final Context context;
    private final String name;

    public ScenarioHas(Context context, String name, ConditionType type) {
        super(type);
        this.context = context;
        this.name = name;
    }

    protected Boolean isPresent() {
        return this.context.findComponentByName(name).isPresent();
    }

}
