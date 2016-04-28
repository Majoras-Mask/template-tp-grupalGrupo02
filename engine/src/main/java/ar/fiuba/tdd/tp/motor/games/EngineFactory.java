package ar.fiuba.tdd.tp.motor.games;

import java.util.Optional;

public interface EngineFactory {
    public Optional<Engine> createGame(String name);
}
