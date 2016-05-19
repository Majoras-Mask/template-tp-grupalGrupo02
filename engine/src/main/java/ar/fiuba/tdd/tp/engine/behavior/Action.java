package ar.fiuba.tdd.tp.engine.behavior;


import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Action implements Behavior {
    Game game;
    private static final String NO_ITEM_ROOM = "There is no such item in this room.";
    protected String noItemInRoom = NO_ITEM_ROOM;


    public Action(Game game) {
        this.game = game;
    }

    public String execute(String completeMessage) {
        Pattern commandPattern = Pattern.compile(regex());
        Matcher commandMatcher = commandPattern.matcher(completeMessage);
        return doAction(commandMatcher, completeMessage);
    }

    abstract String regex();

    abstract ComponentInterface whereToGetThatComponent(Game game, String itemName);
    
    abstract String doAction(Matcher matcher, String message);
}
