package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponentPickable extends GameComponent {

    public GameComponentPickable() {
        super();
    }

    @Override
    public String getDescription() {
        return getBasicName() + String.valueOf(this.id);
    }

    @Override
    public Boolean pick(ZorkTypeGame game) {
        game.addPlayerItem(this);
        game.removeItemFromRoom(this);
        return true;
    }

}
