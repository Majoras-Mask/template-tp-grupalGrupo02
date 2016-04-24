package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentThief extends GameComponents {

    GameComponents whatToSteal;

    public ComponentThief(ZorkTypeGame game, GameComponents whatToSteal) {
        this.game = game;
        this.whatToSteal = whatToSteal;
    }

    public boolean hasComponent(GameComponents whatToGet) {
        for (GameComponents component : this.game.getPlayerItems()) {
            if (component == whatToGet) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getBasicName() {
        return "thief";
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
        if (hasComponent(this.whatToSteal)) {
            this.game.removePlayerItem(this.whatToSteal);
        }
        return true;
    }
}
