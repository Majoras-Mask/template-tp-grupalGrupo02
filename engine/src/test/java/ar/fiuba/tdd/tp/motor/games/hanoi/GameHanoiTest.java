package ar.fiuba.tdd.tp.motor.games.hanoi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevin on 23/04/16.
 */
public class GameHanoiTest {

    private int stackIndexZero = 0;
    private int stackIndexOne = 1;
    private int stackIndexTwo = 2;

    @Test
    public void testIsValidIndex() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);
        assertTrue(gameHanoi.isValidIndex(0));
        assertTrue(gameHanoi.isValidIndex(1));
        assertTrue(gameHanoi.isValidIndex(2));

        assertFalse(gameHanoi.isValidIndex(3));
    }

    @Test
    public void testGetSize() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);

        assertEquals(maxSize, gameHanoi.getSize(0));

    }

    @Test
    public void testGetTopElementFromStack() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);
        assertEquals(1, gameHanoi.getTopElementFromStack(0));
    }

    @Test
    public void testIsValidMove() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);

        gameHanoi.move(stackIndexZero, stackIndexOne);

        assertFalse(gameHanoi.isValidMove(stackIndexZero, stackIndexOne));
        assertTrue(gameHanoi.isValidMove(stackIndexZero, stackIndexTwo));
    }

    @Test
    public void testMove() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);

        gameHanoi.move(stackIndexZero, stackIndexOne);
        assertEquals(maxSize - 1, gameHanoi.getSize(stackIndexZero));
        assertEquals(2, gameHanoi.getTopElementFromStack(stackIndexZero));
        assertEquals(1, gameHanoi.getTopElementFromStack(stackIndexOne));
        assertEquals(1, gameHanoi.getTopElementFromStack(stackIndexOne));

        gameHanoi.move(stackIndexZero, stackIndexTwo);
    }

    @Test
    public void testCheckIfGameIsFinished() throws Exception {
        int maxSize = 3;
        GameHanoi gameHanoi = new GameHanoi(maxSize);

        gameHanoi.move(stackIndexZero, stackIndexTwo);
        gameHanoi.move(stackIndexZero, stackIndexOne);
        gameHanoi.move(stackIndexTwo, stackIndexOne);
        gameHanoi.move(stackIndexZero, stackIndexTwo);
        gameHanoi.move(stackIndexOne, stackIndexZero);
        gameHanoi.move(stackIndexOne, stackIndexTwo);
        gameHanoi.move(stackIndexZero, stackIndexTwo);

        assertTrue(gameHanoi.checkIfGameIsFinished());
    }

}