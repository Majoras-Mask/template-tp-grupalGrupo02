package ar.fiuba.tdd.tp.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public abstract class Game {
    protected Player player;

    public Game() {
        player = new Player();
    }

    abstract boolean winCondition();

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
}
