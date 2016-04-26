package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentThief extends GameComponent {

    public ComponentThief() {
        super();
    }

    @Override
    public String getBasicName() {
        return "thief";
    }

    @Override
    public Boolean talk(ZorkTypeGame game) {
        game.removeAllPlayerItems();
        return true;
    }
}
