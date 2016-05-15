package ar.fiuba.tdd.tp.game.mission.condition;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.context.GameContext;

/**
 * True if a {@link GameContext} has (or not) some {@link Component}.
 */
public class ContextHas extends HasCondition {

    private final GameContext context;
    private final String name;

    public ContextHas(GameContext context, String name, ConditionType type) {
        super(type);
        this.context = context;
        this.name = name;
    }

    protected Boolean isPresent() {
        return this.context.findComponentByName(name).isPresent();
    }

}
