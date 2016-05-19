package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;

public class LookAround implements Behavior {
    Game game;
    private String hasMessage;
    private String messageA;
    private static final String HAS_MESSAGE = "has";
    private static final String A_MESSAGE = "a";

    public LookAround(Game game) {
        this.game = game;
        hasMessage = HAS_MESSAGE;
        messageA = A_MESSAGE;
    }

    public String execute(String modifier) {
        StringBuffer message = new StringBuffer();
        message.append(game.getPlayer().currentRoomName() + " " + hasMessage + ":");
        for (String component : game.getPlayer().listOfWhatISee()) {
            message.append(" " + messageA + " " + component + ".");
        }

        return message.toString();
    }
}