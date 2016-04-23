package ar.fiuba.tdd.tp.motor.game.components;

public abstract class GameComponents {
    public int id;
    private volatile int idCounter = 0;

    public GameComponents() {
        this.id = idCounter++;
    }

    public abstract String getDescription();

    public abstract Boolean pick();

    public abstract Boolean close();

    public abstract Boolean open();
}
