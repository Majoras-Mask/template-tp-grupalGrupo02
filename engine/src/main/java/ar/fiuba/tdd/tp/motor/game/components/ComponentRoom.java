package ar.fiuba.tdd.tp.motor.game.components;

public class ComponentRoom extends GameComponentStoringMultiple {

    public ComponentRoom() {
        super();
    }

    @Override
    public String getBasicName() {
        return "room";
    }

    @Override
    public String whatCanIDo() {
        return "You are currently in this room.";
    }
}
