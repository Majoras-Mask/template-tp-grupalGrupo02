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

    public Boolean pick() {
        return false;
    }

    public Boolean close() {
        return false;
    }

    public Boolean open() {
        return false;
    }

    public Boolean talk() {
        return false;
    }

    public void setGame(ZorkTypeGame game) {
        this.game = game;
    }
}
