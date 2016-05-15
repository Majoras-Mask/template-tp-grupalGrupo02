package ar.fiuba.tdd.tp.game.mission.condition;

import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.Scenario;

/**
 * True if a {@link ar.fiuba.tdd.tp.game.scenario.Scenario} has (or not)
 * some {@link ar.fiuba.tdd.tp.game.player.Player}.
 */
public class ScenarioHas extends HasCondition {

    //TODO This was set protected because CPD fails comparing against ContextHas (although is impossible)
    protected final Player player;
    protected final Scenario scenario;

    public ScenarioHas(ConditionType type, Scenario scenario, Player player) {
        super(type);
        this.player = player;
        this.scenario = scenario;
    }

    protected Boolean isPresent() {
        return this.scenario.getPlayers().stream()
                .anyMatch(onePlayer -> onePlayer.equals(this.player));
    }

}
