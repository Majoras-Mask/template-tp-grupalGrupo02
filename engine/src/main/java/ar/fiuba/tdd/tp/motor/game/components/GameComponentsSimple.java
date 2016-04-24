package ar.fiuba.tdd.tp.motor.game.components;

public abstract class GameComponentsSimple extends GameComponents {

    public GameComponentsSimple() {
        super();
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

    @Override
    public Boolean talk() {
        return false;
    }

    public abstract String getBasicName();

}
