package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.games.hanoi.EngineHanoi;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class EngineFactoryConcrete implements EngineFactory {

    private static EngineFactoryConcrete uniqueInstance = null;
    private final HashMap<String, Supplier<Engine>> engineCreator;

    public EngineFactoryConcrete() {
        this.engineCreator = new HashMap<>();
        this.engineCreator.put("hanoi", this::createEngineHanoi);
        //TODO Etc...

    }

    public static EngineFactoryConcrete getInstance() {
        if ( uniqueInstance == null ) {
            uniqueInstance = new EngineFactoryConcrete();
        }
        return uniqueInstance;
    }

    public Optional<Engine> createGame(String name) {
        Supplier<Engine> supplier = engineCreator.get(name.toLowerCase());
        if (Objects.nonNull(supplier)) {
            return Optional.of(supplier.get());
        }
        return Optional.empty();
    }

    public Engine createGameFetch() {
        return new EngineZork(new GameFetch());
    }

    public Engine createEngineHanoi() {
        return new EngineHanoi();
    }

}
