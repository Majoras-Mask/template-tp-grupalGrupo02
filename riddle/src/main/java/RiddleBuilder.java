import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RiddleBuilder implements GameBuilder {
    //Names of items, commands and responses
    private static final String GAME_NAME = "Riddle";

    private static final String WON_GAME = "You delivered all the items to the other side, you won!";
    private static final String HELP_MESSAGE = "help!!!";

    public Game build() {
        Game riddle = new Game() {
            @Override
            public boolean winCondition() {
                return GameRules.animalsOnTheOtherSide();
            }

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            public String help() {
                return HELP_MESSAGE;
            }

            @Override
            protected String wonMessage() {
                return WON_GAME;
            }
        };

        BuildLevel.build(riddle);

        return riddle;
    }
}