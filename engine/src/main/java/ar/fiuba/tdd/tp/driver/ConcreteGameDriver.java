package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.BuilderLoader;
import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameState;

/**
 * Created by kevin on 16/06/16.
 */
public class ConcreteGameDriver implements GameDriver {
    private Game game;

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException {
        try {
            this.game = BuilderLoader.load(jarPath).build();
        } catch (Exception e) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public String joinPlayer() throws PlayerJoinFailedException {
        String playerID = game.getPlayerIDAvailable();
        if (playerID.isEmpty()) {
            throw new PlayerJoinFailedException();
        }
        return playerID;
    }

    @Override
    public String sendCommand(String player, String cmd) throws UnknownPlayerException {
        if (player.isEmpty()) {
            throw new UnknownPlayerException();
        }

        return game.executeCommand(player, cmd);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public GameState getCurrentState() {
        return game.getGameState();
    }

    @Override
    public boolean checkIfPlayerHasLost(String player) {
        return game.playerLose(player);
    }
}
