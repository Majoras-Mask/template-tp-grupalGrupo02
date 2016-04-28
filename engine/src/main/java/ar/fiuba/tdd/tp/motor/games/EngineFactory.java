package ar.fiuba.tdd.tp.motor.games;

public interface EngineFactory {
    Engine createGameFetch();

    Engine createEngineHanoi();

    Engine createEngineRiddle();

    Engine createEngineOpenDoor();

    Engine createEngineOpenDoorTwo();

    Engine createEngineCursedObject();

    Engine createEngineTreasureHunt();
}
