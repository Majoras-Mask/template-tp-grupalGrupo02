package ar.fiuba.tdd.tp.server;

public interface GameDriver {
    void initGame(String jarPath);

    String sendCommand(String cmd);
}
