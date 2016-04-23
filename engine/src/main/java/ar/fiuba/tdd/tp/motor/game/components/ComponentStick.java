package ar.fiuba.tdd.tp.motor.game.components;


public class ComponentStick implements GameComponents {
    int id;
    private volatile int idCounter = 0;

    public ComponentStick() {
        this.id = idCounter++;
    }

    @Override
    public String getDescription() {
        return "Stick" + String.valueOf(this.id);
    }

    @Override
    public Boolean pick() {
        return true;
    }
}
