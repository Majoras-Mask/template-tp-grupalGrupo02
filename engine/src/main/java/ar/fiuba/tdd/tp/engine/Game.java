package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public abstract class Game {
    protected Content player;

    public Game() {
        player = new Content("player");
    }

    protected abstract String doCommand(String string);
    protected abstract boolean winCondition();

    public String command(String string) {
        if (winCondition()) {
            return "You won the game!";
        } else {
            String response = doCommand(string);
            if (winCondition()) {
                response = "You won the game!";
            }
            return response;
        }
    }
}