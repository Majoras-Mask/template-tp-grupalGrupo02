package ar.fiuba.tdd.tp.motor.games.riddle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameRiddleTest {

    private void moving(EngineRiddle engineRiddle) {
        engineRiddle.request("take sheep");
        engineRiddle.request("cross north-shore");
        engineRiddle.request("leave sheep");
        engineRiddle.request("cross south-shore");
        engineRiddle.request("take cabbage");
        engineRiddle.request("cross north-shore");
        engineRiddle.request("leave cabbage");
    }

    private void winningMovements(EngineRiddle engine) {
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("leave sheep");
        engine.request("cross south-shore");
        engine.request("take cabbage");
        engine.request("cross north-shore");
        engine.request("leave cabbage");
        engine.request("take sheep");
        engine.request("cross south-shore");
        engine.request("leave sheep");
        engine.request("take wolf");
        engine.request("cross north-shore");
        engine.request("leave wolf");
        engine.request("cross south-shore");
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("leave sheep");
    }

    @Test
    public void fullRiddleTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        winningMovements(engine);
        assertTrue(game.checkIfGameIsFinished());
    }

    @Test
    public void takeSheepTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("take sheep"),"Ok.");
    }

    @Test
    public void takeCabbageTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("take cabbage"),"Ok.");
    }

    @Test
    public void takeWolfTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("take wolf"),"Ok.");
    }

    @Test
    public void cantCrossTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("cross south-shore"),"You can't do that! You are already on the south island.");
    }

    @Test
    public void goEmptyTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("cross north-shore"),"You can't do that! The wolf will eat the sheep!");
    }

    @Test
    public void leaveEmptyTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("leave wolf"),"You can't do that! There's no wolf on the boat.");
    }

    @Test
    public void takeGoAndleaveTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        assertEquals(engine.request("leave sheep"),"Ok.");
    }

    @Test
    public void takeGoAndleaveWrongTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        assertEquals(engine.request("leave wolf"),"You can't do that! There's no wolf on the boat.");
    }

    @Test
    public void takeAndleaveTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take wolf");
        assertEquals(engine.request("leave wolf"),"Ok.");
    }

    @Test
    public void takeWolfAndLeaveTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take wolf");
        assertEquals(engine.request("cross north-shore"),"You can't do that! The sheep will eat the cabbage!");
    }

    @Test
    public void takeWolfTryCrossLeaveWolfTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take wolf");
        engine.request("cross north-shore");
        assertFalse(game.checkIfGameIsFinished());
        assertEquals(engine.request("leave wolf"),"Ok.");
    }

    @Test
    public void takeSheepCrossLeaveSheepCrossWrongTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("leave sheep");
        assertEquals(engine.request("cross north-shore"),"You can't do that! You are already on the north island.");
    }

    @Test
    public void takeSheepCrossAndComeBackWithSheepTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        assertEquals(engine.request("cross south-shore"),"You have crossed!");
    }

    @Test
    public void takeSheepCrossAndComeBackWithSheepLeaveTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("cross south-shore");
        engine.request("cross north-shore");
        engine.request("cross south-shore");
        assertEquals(engine.request("leave sheep"),"Ok.");
    }

    @Test
    public void wolfAndSheepNorthShoreTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("leave sheep");
        engine.request("cross south-shore");
        engine.request("take wolf");
        engine.request("cross north-shore");
        engine.request("leave wolf");
        assertEquals(engine.request("cross south-shore"),"You can't do that! The wolf will eat the sheep!");
    }

    @Test
    public void cabbageAndSheepNorthShoreTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        moving(engine);
        assertEquals(engine.request("cross south-shore"),"You can't do that! The sheep will eat the cabbage!");
    }

    @Test
    public void wrongCommandTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        assertEquals(engine.request("takssssse sheeeeeeeep"),"No es posible procesar el comando.");
    }

    @Test
    public void fullBoatTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        assertEquals(engine.request("take wolf"),"You can't do that! The boat is full.");
    }

    @Test
    public void doubleSheepTest() {
        GameRiddle game = new GameRiddle();
        EngineRiddle engine = new EngineRiddle(game);
        engine.request("take sheep");
        engine.request("cross north-shore");
        engine.request("leave sheep");
        engine.request("cross south-shore");
        assertEquals(engine.request("take sheep"),"You can't do that! There's no sheep on the island.");
    }





}
