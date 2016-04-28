package ar.fiuba.tdd.tp.motor.games;

import java.util.Optional;

public interface EngineFactory {
    public Engine createGame(String name);
}
