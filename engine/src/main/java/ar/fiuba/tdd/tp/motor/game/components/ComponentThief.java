package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentThief extends GameComponents {

    ZorkTypeGame game;

    public ComponentThief(ZorkTypeGame game) {
        this.game = game;
        this.componentName = "Thief";
    }

    @Override
    public Boolean pick() {
        return false;
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
        //TODO robar cosas
        this.game.getPlayerItems();
        return true;
    }
}
