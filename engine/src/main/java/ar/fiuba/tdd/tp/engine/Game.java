package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public abstract class Game {
    protected Player player;

    public Game() {
        player = new Player();
    }

    abstract boolean winCondition();

    public String command(String string) {
        if (winCondition()) {
            return "You won the game!";
        } else {
            String response = player.doCommand(string);
            if (winCondition()) {
                response = "You won the game!";
            }
            return response;
        }
    }
}
