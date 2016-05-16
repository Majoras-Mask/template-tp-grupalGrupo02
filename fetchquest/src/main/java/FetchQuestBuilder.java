import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.mission.Mission;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

public class FetchQuestBuilder implements GameBuilder {

    @Override
    public Game build() {
        Game fetchQuest = new Game() {
            final Context playerContext = RoomsBuilder.roomContext();
            private final Player player = PlayerBuilder.playerImplementation(playerContext);
            private final Mission mission = RoomsBuilder.createMission(player);
            private static final String GAME_NAME = "Fetch Quest";
            private static final String INSTRUCTIONS = "GAME COMMANDS:\n open X, close X, look around, consume X, store X in Y, what can i do with X, talk X, pick X";

            @Override
            public String doCommand(String command) {
                return this.player.doCommand(command);
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public Boolean isWon() {
                return this.mission.isAccomplished();
            }

            @Override
            public Boolean isLost() {
                return this.mission.isFailed();
            }

            @Override
            public String help() {
                return INSTRUCTIONS;
            }
        };
        return fetchQuest;
    }
}
