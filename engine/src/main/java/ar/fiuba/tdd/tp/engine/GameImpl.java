package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.player.Player;

public abstract class GameImpl implements Game {

    protected Player player;

    public GameImpl(Player player) {
        this.player = player;
    }

    @Override
    public Boolean isFinished() {
        //TODO ver como hacer esto
        return false;
    }

    @Override
    public String doCommand(String clientMessage) {
        String message = player.doCommand(clientMessage);
        if (isFinished()) {
            return "Game over.";
        }
        return message;
    }
}
