package ar.fiuba.tdd.tp.engine;

public abstract class Game {
    protected Player player;

    public Game() {
        player = new Player();
    }

    public abstract boolean winCondition();

    public Player getPlayer() {
        return player;
    }

    public String command(String clientMessage) {
        if (winCondition()) {
            return "You won the game!";
        } else {
            String response = player.doCommand(clientMessage);
            if (winCondition()) {
                response = "You won the game!";
            }
            return response;
        }
    }

    public abstract String noItemInRoom();
}
