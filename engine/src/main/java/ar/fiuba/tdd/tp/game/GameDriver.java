package ar.fiuba.tdd.tp.game;

public interface GameDriver {
    void initGame(String jarPath);

    String sendCommand(String cmd);
}