package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class OpenConditionObject implements OpenCondition {
    private boolean withObjectOrNo;
    private GameComponentPickable object;

    public OpenConditionObject(GameComponentPickable object, boolean withObjectOrNo) {
        this.withObjectOrNo = withObjectOrNo;
        this.object = object;
    }

    public boolean mustOpen(ZorkTypeGame game) {
        return (game.getPlayerItems().contains(object) == withObjectOrNo);
    }

}