package ar.fiuba.tdd.tp.engine.behavior;


import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectAction implements Behavior {
    private static final String DIRECT_ACTION_REGEX = "(^.*) (.*)";
    Game game;

    public DirectAction(Game game) {
        this.game = game;
    }

    @Override
    public String execute(String completeMessage) {
        Pattern commandPattern = Pattern.compile(DIRECT_ACTION_REGEX);
        Matcher commandMatcher = commandPattern.matcher(completeMessage);
        ComponentInterface component = null;
        if (commandMatcher.find()) {
            component = game.getPlayer().obtainItemRoom(commandMatcher.group(2));
        }
        if (component != null) {
            return component.doAction(commandMatcher.group(1), completeMessage);
        }

        return game.noItemInRoom();
    }
}