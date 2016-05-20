package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DirectAction extends Action {
    private static final String DIRECT_ACTION_REGEX = "(^\\w*) ";

    public DirectAction(Game game) {
        super(game);
    }

    public String doAction(Matcher matcher, String completeMessage) {
        ComponentInterface component = null;
        if (matcher.find()) {
            component = whereToGetThatComponent(game, completeMessage.replaceFirst(matcher.group(0), ""));
        }
        if (component != null) {
            return component.doAction(matcher.group(1), completeMessage);
        }

        return noItemInRoom;
    }

    String regex() {
        return DIRECT_ACTION_REGEX;
    }
}
