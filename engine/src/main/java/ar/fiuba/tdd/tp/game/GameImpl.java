package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.game.player.Player;

public class GameImpl implements Game {

    protected String gameName;
    protected String help;
    protected Player player;

    @Override
    public String getName() {
        return gameName;
    }

    @Override
    public Boolean isFinished() {
        //TODO ver como hacer esto
        return false;
    }

    @Override
    public String help() {
        return help;
    }

    @Override
    public String doCommand(String clientMessage) {
        String message = player.doCommand(clientMessage);
        if (isFinished()) {
            return "Game over.";
        }
        return message;
    }


    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
