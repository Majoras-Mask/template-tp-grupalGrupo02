package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.player.Player;

public abstract class Game {
    public Player player = new Player();

    private GameState gameState;

    public void lostGame() {
        this.gameState = GameState.LOST;
    }

    public void wonGame() {
        this.gameState = GameState.WON;
    }

    public void readyGame() {
        this.gameState = GameState.READY;
    }

    public GameState getGameState() {
        return gameState;
    }

    public abstract boolean winCondition();

    public String command(String clientMessage) {
        String response = player.doCommand(clientMessage.toLowerCase());
        if (winCondition()) {
            response = wonMessage();
        }
        return response;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract String getName();

    public abstract String help();

    protected abstract String wonMessage();
}
