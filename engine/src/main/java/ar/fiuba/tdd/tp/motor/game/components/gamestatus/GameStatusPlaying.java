package ar.fiuba.tdd.tp.motor.game.components.gamestatus;

public class GameStatusPlaying implements GameStatus {
    @Override
    public String statusMessage() {
        return "Playing.";
    }
}
