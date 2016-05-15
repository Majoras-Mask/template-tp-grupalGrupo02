package ar.fiuba.tdd.tp.game.mission.condition;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;

/**
 * True if a {@link Player} has (or not) some {@link Component}.
 */
public class PlayerHas extends HasCondition {

    private final Player player;
    private final String componentName;

    public PlayerHas(Player player, String componentName, ConditionType type) {
        super(type);
        this.player = player;
        this.componentName = componentName;
    }

    @Override
    protected Boolean isPresent() {
        return this.player.has(componentName);
    }
}
