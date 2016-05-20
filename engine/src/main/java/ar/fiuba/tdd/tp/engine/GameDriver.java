package ar.fiuba.tdd.tp.engine;

public interface GameDriver {
    void initGame(String jarPath);

    String sendCommand(String cmd);

    GameState getGameState();
}