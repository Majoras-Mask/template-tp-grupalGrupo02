package ar.fiuba.tdd.tp.engine.player;

import ar.fiuba.tdd.tp.engine.player.status.PlayerStatus;

public class PlayerMortal extends Player {
    PlayerStatus currentStatus = PlayerStatus.HEALTHY;

    public PlayerStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(PlayerStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
}
