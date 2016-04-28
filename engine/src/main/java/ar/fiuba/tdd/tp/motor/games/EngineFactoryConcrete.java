package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.games.hanoi.EngineHanoi;

public class EngineFactoryConcrete implements EngineFactory {
    private static EngineFactoryConcrete uniqueInstance = null;

    private EngineFactoryConcrete() {

    }

    public static EngineFactoryConcrete getInstance() {
        if ( uniqueInstance == null ) {
            uniqueInstance = new EngineFactoryConcrete();
        }
        return uniqueInstance;
    }

    public Engine createEngineHanoi() {
        return new EngineHanoi();
    }

}