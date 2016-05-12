package ar.fiuba.tdd.tp.engine;

public abstract class Game {

    public abstract boolean winCondition();

    public abstract String command(String clientMessage);

    public abstract Player getPlayer();

    public abstract String getName();
}
