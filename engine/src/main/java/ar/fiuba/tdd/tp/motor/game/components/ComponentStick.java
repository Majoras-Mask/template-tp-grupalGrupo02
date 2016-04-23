package ar.fiuba.tdd.tp.motor.game.components;


public class ComponentStick implements GameComponents {
    String id;

    public ComponentStick(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }
}
