import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class FetchQuestBuilder implements GameBuilder {

    private ComponentInterface winningCondition;

    //Names of items, commands and responses
    private static final String GAME_NAME = "Fetch Quest";

    private static final String WON_GAME = "You picked the stick and won the game!";
    private static final String HELP_MESSAGE = "help!!!";

    public Game build() {
        Game fetchQuest = new Game() {
            public Player player = new Player();

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
            public String command(String clientMessage) {
                String response = player.doCommand(clientMessage.toLowerCase());
                if (winCondition()) {
                    wonGame();
                    response = WON_GAME;
                }
                return response;
            }
        };

        BuildLevel.build(fetchQuest);

        winningCondition = BuildLevel.getWinningCondition();

        return fetchQuest;
    }
}
