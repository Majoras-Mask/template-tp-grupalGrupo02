package ar.fiuba.tdd.tp.conditions;


import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameState;

/**
 * Created by kevin on 31/05/16.
 */
public class ConditionCheckGameState implements Condition {

    private Game game;
    private GameState gameState;

    public ConditionCheckGameState(Game game, GameState gamestate) {
        this.game = game;
        this.gameState = gamestate;
    }

    @Override
    public boolean check(Context context) {
        return game.getGameState() == gameState;
    }
}
