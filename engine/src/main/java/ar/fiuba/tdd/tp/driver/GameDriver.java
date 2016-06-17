package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameState;

public interface GameDriver {
    void initGame(String jarPath) throws GameLoadFailedException;

    String joinPlayer() throws PlayerJoinFailedException;

    String sendCommand(String player, String cmd) throws UnknownPlayerException;

    Game getGame();

    GameState getCurrentState();

    boolean checkIfPlayerHasLost(String player);
}
