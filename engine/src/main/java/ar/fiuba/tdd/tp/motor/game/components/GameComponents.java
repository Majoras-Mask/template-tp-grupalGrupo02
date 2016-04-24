package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponents {
    public int id;
    private volatile int idCounter = 0;
    public ZorkTypeGame game;

    public GameComponents() {
        this.id = idCounter++;
    }

    public String getDescription() {
        return (String.format("%s%s", getBasicName(), String.valueOf(this.id)));
    }

    public abstract String getBasicName();

    public abstract Boolean pick();

    public abstract Boolean close();

    public abstract Boolean open();

    public abstract Boolean talk();

    public void setGame(ZorkTypeGame game) {
        this.game = game;
    }
}
