package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class ComponentAntidoto extends GameComponentPickable {

    @Override
    public String getBasicName() {
        return "antidoto";
    }

    @Override
    public Boolean consume(ZorkTypeGame game) {
        game.getGamePlayer().setAntidoto();
        return true;
    }
}
