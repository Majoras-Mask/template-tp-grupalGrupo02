package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameState;

/**
 * Created by kevin on 31/05/16.
 */
public class ActionSetGameState implements Action {

    private Game game;
    private GameState gameState;

    public ActionSetGameState(Game game, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
    }

    @Override
    public void execute(Context context) {
        game.setGameState(gameState);
    }
}
