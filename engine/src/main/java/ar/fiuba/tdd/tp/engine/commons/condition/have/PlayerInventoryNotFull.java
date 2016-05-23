package ar.fiuba.tdd.tp.engine.commons.condition.have;


import ar.fiuba.tdd.tp.engine.commons.condition.Condition;
import ar.fiuba.tdd.tp.engine.player.Player;

public class PlayerInventoryNotFull implements Condition {

    Player player;

    public PlayerInventoryNotFull(Player player) {
        this.player = player;
    }

    @Override
    public Boolean accomplished() {
        return !player.getInventory().limitReached();
    }
}
