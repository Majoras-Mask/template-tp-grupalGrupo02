package ar.fiuba.tdd.tp.motor.games.hanoi;

import ar.fiuba.tdd.tp.motor.games.Game;

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
        return ( stackIndex >= 0  && stackIndex < 3 );
    }

    public int getSize(int stackIndex) {
        return this.stacks.get(stackIndex).size();
    }

    public int getTopElementFromStack(int stackIndex) {
        return this.stacks.get(stackIndex).peek();
    }

    public boolean isValidMove(int fromStackIndex, int toStackIndex) {
        if (!isValidIndex(fromStackIndex) || !isValidIndex(toStackIndex)
                || getSize(fromStackIndex) == 0) {
            return false;
        }

        if ( getSize(toStackIndex) == 0 ) {
            return true;
        }

        int topFromStackIndex = this.stacks.get(fromStackIndex).peek();
        int topToStackIndex = this.stacks.get(toStackIndex).peek();

        return topFromStackIndex < topToStackIndex;
    }

    public void move(int fromStackIndex, int toStackIndex) {
        if (!isValidMove(fromStackIndex, toStackIndex)) {
            return;
        }

        int topFromStackIndex = this.stacks.get(fromStackIndex).pop();
        this.stacks.get(toStackIndex).push(topFromStackIndex);
    }

    public boolean checkIfGameIsFinished() {
        return (this.getSize(2) == this.stackFloors);
    }

    @Override
    public String finishedMessage() {
        return "You win the game.";
    }

    @Override
    public String help() {
        return null;
    }

    public String welcomeMessage() {
        //TODO mejorar descripción
        return "Welcome to the Hanoi game.\\n"
                + "The oject of this game is to move all the disks over to Tower 3.\\n"
                + "But you cannot place a larger disk onto a smaller disk.";
    }
}
