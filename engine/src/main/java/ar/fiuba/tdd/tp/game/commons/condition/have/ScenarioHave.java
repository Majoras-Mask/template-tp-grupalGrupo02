package ar.fiuba.tdd.tp.game.commons.condition.have;

import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.Scenario;

/**
 * True if a {@link ar.fiuba.tdd.tp.game.scenario.Scenario} has (or not)
 * some {@link ar.fiuba.tdd.tp.game.player.Player}.
 */
public class ScenarioHave extends HaveCondition {

    //TODO This was set protected because CPD fails comparing against ContextHave (although is impossible)
    protected final Player player;
    protected final Scenario scenario;

    public ScenarioHave(HaveType type, Scenario scenario, Player player) {
        super(type);
        this.player = player;
        this.scenario = scenario;
    }

    protected Boolean isPresent() {
        return this.scenario.getPlayers().stream()
                .anyMatch(onePlayer -> onePlayer.equals(this.player));
    }

}
