package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.regex.Matcher;

public class IndirectAction extends Action {
    private static final String DIRECT_ACTION_REGEX = "(^.*) (.*) (.*$)";

    public IndirectAction(Game game) {
        super(game);
    }

    public String doAction(Matcher matcher, String completeMessage) {
        ComponentInterface component = null;
        ComponentInterface receiverOfAction = null;
        if (matcher.find()) {
            component = whereToGetThatComponent(game, matcher.group(2));
            receiverOfAction = whereToGetThatComponent(game, matcher.group(3));
        }
        if (component != null && receiverOfAction != null) {
            return receiverOfAction.doAction(matcher.group(1), matcher.group(2));
        }

        return "You are missing an item to perform this action.";
    }

    String regex() {
        return DIRECT_ACTION_REGEX;
    }

    @Override
    ComponentInterface whereToGetThatComponent(Game game, String itemName) {
        ComponentInterface component = game.getPlayer().obtainItemRoom(itemName);
        if (component == null) {
            return game.getPlayer().obtainItemInventory(itemName);
        }
        return component;
    }
}
