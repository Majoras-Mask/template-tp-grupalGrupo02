package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentThief extends GameComponent {

    GameComponent whatToSteal;

    public ComponentThief(ZorkTypeGame game, GameComponent whatToSteal) {
        this.game = game;
        this.whatToSteal = whatToSteal;
    }

    public boolean hasComponent(GameComponent whatToGet) {
        for (GameComponent component : this.game.getPlayerItems()) {
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
    public Boolean talk() {
        //TODO robar cosas
        if (hasComponent(this.whatToSteal)) {
            this.game.removePlayerItem(this.whatToSteal);
        }
        return true;
    }
}
