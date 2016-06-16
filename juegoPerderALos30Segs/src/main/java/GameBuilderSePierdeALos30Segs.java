import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.ActionNull;
import ar.fiuba.tdd.tp.actions.ActionSetGameState;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.commands.CommandConcreteRegex;
import ar.fiuba.tdd.tp.conditions.ConditionAlwaysTrue;
import ar.fiuba.tdd.tp.timer.TimerConcrete;

@SuppressWarnings("CPD-START")
public class GameBuilderSePierdeALos30Segs implements GameBuilder {

    private static final String PLAYER_NAME = "player";
    private static final String MESSAGE_TIME_OUT = "Pasaron los 30 segs, se perdio el juego.";
    private static final String COMMAND_HELP = "help";
    private static final String MESSAGE_HELP = "Este es un juego de prueba. Pasado los 30 segs, se perdera el juego.";

    public Game build() {
        GameConcrete game = new GameConcrete();

        Command command = new CommandConcreteRegex(COMMAND_HELP);
        game.addCommand(command);
        command.setCondition(
                new ConditionAlwaysTrue(),
                new ActionNull(),
                MESSAGE_HELP
        );

        ObjectConcrete player = new ObjectConcrete(PLAYER_NAME);
        game.addPlayer(player);

        TimerConcrete timer = new TimerConcrete(30*1000);
        timer.setCondition(
                new ConditionAlwaysTrue(),
                new ActionSetGameState(game, GameState.Lost),
                MESSAGE_TIME_OUT
        );
        game.addTimer(timer);

        return game;
    }
}
