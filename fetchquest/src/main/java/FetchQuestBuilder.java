import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.mission.Mission;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

public class FetchQuestBuilder implements GameBuilder {
    private static final String GAME_NAME = "Fetch Quest";
    private static final String INSTRUCTIONS = "GAME COMMANDS:\n open X, close X, look around,"
            + " consume X, store X in Y, what can i do with X, talk X, pick X";

    @Override
    public Game build() {
        Game fetchQuest = new Game() {
            final Context playerContext = RoomsBuilder.roomContext();
            private final Player player = PlayerBuilder.playerImplementation(playerContext);
            private final Mission mission = RoomsBuilder.createMission(player);

            @Override
            public String doCommand(String command) {
                String message = this.player.doCommand(command);

                if (isFinished()) {
                    return "msg";
                }
                return message;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public Boolean isFinished() {
                return (this.mission.isAccomplished() || this.mission.isFailed());
            }

            @Override
            public String help() {
                return INSTRUCTIONS;
            }
        };
        return fetchQuest;
    }
}
