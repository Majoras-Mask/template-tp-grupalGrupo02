package ar.fiuba.tdd.tp.motor.game.components;


import java.util.LinkedList;
import java.util.List;

public abstract class GameComponentStoring extends GameComponent {

    public GameComponentStoring() {
        super();
    }

    @Override
    public String whatCanIDo() {
        return "You can open/close this.";
    }
}
