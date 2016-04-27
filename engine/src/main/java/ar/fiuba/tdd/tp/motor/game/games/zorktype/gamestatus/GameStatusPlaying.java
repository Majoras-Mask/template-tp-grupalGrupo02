package ar.fiuba.tdd.tp.motor.game.games.zorktype.gamestatus;

public class GameStatusPlaying implements GameStatus {
    @Override
    public String statusMessage() {
        return "Playing.";
    }
}
