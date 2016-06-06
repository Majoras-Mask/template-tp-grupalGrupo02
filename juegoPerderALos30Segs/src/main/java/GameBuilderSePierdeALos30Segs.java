import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.ActionNull;
import ar.fiuba.tdd.tp.actions.ActionSetGameState;
import ar.fiuba.tdd.tp.conditions.ConditionAlwaysTrue;
import ar.fiuba.tdd.tp.timer.TimerConcrete;

@SuppressWarnings("CPD-START")
public class GameBuilderSePierdeALos30Segs implements GameBuilder {

    private static final String PLAYER_NAME = "player";
    private static final String MESSAGE_TIME_OUT = "Pasaron los 30 segs, se perdio el juego.";

    public Game build() {
        GameConcrete game = new GameConcrete();

        ObjectConcrete player = new ObjectConcrete(PLAYER_NAME);
        game.addPlayer(player);

        TimerConcrete timer = new TimerConcrete(30);
        timer.setCondition(
                new ConditionAlwaysTrue(),
                new ActionSetGameState(game, GameState.Lost),
                MESSAGE_TIME_OUT
        );
        game.addTimer(timer);

        return game;
    }
}
