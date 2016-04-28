package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class ComponentAntidote extends GameComponentPickable {

    @Override
    public String getBasicName() {
        return "antidote";
    }

    @Override
    public String consume(ZorkTypeGame game) {
        game.getGamePlayer().setAntidoto();
        return true;
    }
}
