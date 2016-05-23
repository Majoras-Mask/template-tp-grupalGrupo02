package ar.fiuba.tdd.tp.engine.commons.condition.have;

import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.player.Player;

/**
 * True if a {@link Player} has (or not) some {@link Component}.
 */
public class PlayerHave extends HaveCondition {

    private final Player player;
    private final String componentName;

    public PlayerHave(Player player, String componentName, HaveType type) {
        super(type);
        this.player = player;
        this.componentName = componentName;
    }

    @Override
    protected Boolean isPresent() {
        return this.player.has(componentName);
    }
}
