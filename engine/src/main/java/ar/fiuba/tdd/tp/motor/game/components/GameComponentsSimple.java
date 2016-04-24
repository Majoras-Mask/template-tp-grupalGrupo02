package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

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
        this.game.addPlayerItem(this);
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

    public void setGame(ZorkTypeGame game) {
        this.game = game;
    }
}
