package ar.fiuba.tdd.tp.motor.games.hanoi;

import ar.fiuba.tdd.tp.motor.Game;

import java.util.ArrayList;
import java.util.Stack;

public class GameHanoi implements Game {

    private int stackFloors;
    private ArrayList<Stack<Integer>> stacks;

    private void initStacks() {
        for (int i = 0; i < 3; i ++) {
            this.stacks.add(new Stack<Integer>());
        }

        Stack<Integer> stack = this.stacks.get(0);
        for (int i = this.stackFloors; i > 0; i-- ) {
            stack.push(i);
        }
    }

    public GameHanoi(int stackFloors) {
        this.stackFloors = stackFloors;
        this.stacks = new ArrayList<Stack<Integer>>();
        this.initStacks();
    }

    public boolean isValidIndex(int stackIndex) {
        return ( stackIndex >= 1  || stackIndex <= 3 );
    }

    public int getSize(int stackIndex) {
        return this.stacks.get(stackIndex - 1).size();
    }

    public int getTopElementFromStack(int stackIndex) {
        return this.stacks.get(stackIndex - 1).peek();
    }

    public boolean isValidMove(int fromStackIndex, int toStackIndex) {
        if (!isValidIndex(fromStackIndex) || !isValidIndex(toStackIndex)
                || getSize(fromStackIndex) == 0) {
            return false;
        }

        if ( getSize(toStackIndex) == 0 ) {
            return true;
        }

        int topFromStackIndex = this.stacks.get(fromStackIndex - 1).peek();
        int topToStackIndex = this.stacks.get(toStackIndex - 1).peek();

        return topFromStackIndex < topToStackIndex;
    }

    public void move(int fromStackIndex, int toStackIndex) throws InvalidOperation {
        if (!isValidMove(fromStackIndex, toStackIndex)) {
            throw new InvalidOperation();
        }

        int topFromStackIndex = this.stacks.get(fromStackIndex - 1).pop();
        this.stacks.get(toStackIndex - 1).push(topFromStackIndex);
    }

    public boolean checkIfGameIsFinished() {
        return (this.getSize(2) == this.stackFloors);
    }
}
