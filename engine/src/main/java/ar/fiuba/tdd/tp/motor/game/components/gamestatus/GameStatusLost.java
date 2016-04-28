package ar.fiuba.tdd.tp.motor.game.components.gamestatus;

/**
 * Created by kevin on 28/04/16.
 */
public class GameStatusLost implements GameStatus {
    @Override
    public String statusMessage() {
        return "You lost the game.";
    }
}
