package ar.fiuba.tdd.tp.engine;

public abstract class Game {

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

    public abstract String command(String clientMessage);

    public abstract Player getPlayer();

    public abstract String getName();

    public abstract String help();
}
