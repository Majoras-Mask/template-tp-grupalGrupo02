package ar.fiuba.tdd.tp.motor.games;


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


    @Override
    public Engine createEngineFetch() {
        return null;
    }

    @Override
    public Engine createEngineHanoi() {
        return null;
    }

    @Override
    public Engine createEngineRiddle() {
        return null;
    }

    @Override
    public Engine createEngineOpenDoor() {
        return null;
    }

    @Override
    public Engine createEngineOpenDoorTwo() {
        return null;
    }

    @Override
    public Engine createEngineCursedObject() {
        return null;
    }

    @Override
    public Engine createEngineTreasureHunt() {
        return null;
    }
}
