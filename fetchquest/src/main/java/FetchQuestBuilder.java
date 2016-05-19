import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.player.Player;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

@SuppressWarnings("CPD-START")
public class FetchQuestBuilder implements GameBuilder {

    private ComponentInterface winningCondition;

    //Names of items, commands and responses
    private static final String GAME_NAME = "Fetch Quest";

    private static final String WON_GAME = "You picked the stick and won the game!";
    private static final String HELP_MESSAGE = "help!!!";

    public Game build() {
        Game fetchQuest = new Game() {
            @Override
            public boolean winCondition() {
                return this.getPlayer().playerHasItem(winningCondition);
            }

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public String help() {
                return HELP_MESSAGE;
            }

            @Override
            protected String wonMessage() {
                return WON_GAME;
            }
        };

        BuildLevel.build(fetchQuest);

        winningCondition = BuildLevel.getWinningCondition();

        fetchQuest.readyGame();

        return fetchQuest;
    }
}
