package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;

public class LookAround implements Behavior {
    Game game;
    private String hasMessage = HAS_MESSAGE;
    private static final String HAS_MESSAGE = "has";

    public LookAround(Game game) {
        this.game = game;
    }

    public String execute(String modifier) {
        StringBuffer message = new StringBuffer();
        message.append(game.getPlayer().currentRoomName() + " " + hasMessage + ":");
        for (String component : game.getPlayer().listOfWhatISee()) {
            message.append(" " + component + ".");
        }

        return message.toString();
    }
}