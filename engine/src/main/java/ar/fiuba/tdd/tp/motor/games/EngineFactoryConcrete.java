package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.EngineZork;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.cursedobject.GameCursedObject;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.fetch.GameFetch;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoor.GameOpenDoor;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoortwo.GameOpenDoorTwo;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.treasurehunt.GameTreasureHunt;
import ar.fiuba.tdd.tp.motor.games.hanoi.EngineHanoi;
import ar.fiuba.tdd.tp.motor.games.hanoi.GameHanoi;
import ar.fiuba.tdd.tp.motor.games.riddle.EngineRiddle;
import ar.fiuba.tdd.tp.motor.games.riddle.GameRiddle;


public class EngineFactoryConcrete implements EngineFactory {

    private static EngineFactoryConcrete uniqueInstance = null;
    private static final int defaultHanoiStacks = 3;

    private EngineFactoryConcrete() {
    }

    public static EngineFactoryConcrete getInstance() {
        if ( uniqueInstance == null ) {
            uniqueInstance = new EngineFactoryConcrete();
        }
        return uniqueInstance;
    }

    public Engine createEngineFetch() {
        return new EngineZork(new GameFetch());
    }

    public Engine createEngineHanoi() {
        return new EngineHanoi(new GameHanoi(this.defaultHanoiStacks));
    }

    public Engine createEngineRiddle() {
        return new EngineRiddle(new GameRiddle());
    }

    public Engine createEngineOpenDoor() {
        return new EngineZork(new GameOpenDoor());
    }

    public Engine createEngineOpenDoorTwo() {
        return new EngineZork(new GameOpenDoorTwo());
    }

    public Engine createEngineCursedObject() {
        return new EngineZork(new GameCursedObject());
    }

    public Engine createEngineTreasureHunt() {
        return new EngineZork(new GameTreasureHunt());
    }


}
