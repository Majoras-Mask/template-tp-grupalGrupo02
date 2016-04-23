package ar.fiuba.tdd.tp.motor.game.components;

public abstract class GameComponentsSimple implements GameComponents {
    int id;
    private volatile int idCounter = 0;

    public GameComponentsSimple() {
        this.id = idCounter++;
    }

    @Override
    public String getDescription() {
        return getBasicName() + String.valueOf(this.id);
    }

    @Override
    public Boolean pick() {
        return true;
    }

    @Override
    public Boolean close() {
        return false;
    }

    @Override
    public Boolean open() {
        return false;
    }

    public abstract String getBasicName();

}
