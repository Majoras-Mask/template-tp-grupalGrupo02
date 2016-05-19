import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenDoorTwoBuilder implements GameBuilder {

    private static final String GAME_NAME = "Open Door Two";


    private static final String WON_GAME = "You won the game by opening the door!";
    private static final String HELP_MESSAGE = "help!!!";


    public Game build() {
        Game openDoorTwo = new Game() {
            @Override
            public boolean winCondition() {
                return false;
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

        BuildLevel.build(openDoorTwo);

        return openDoorTwo;
    }
}