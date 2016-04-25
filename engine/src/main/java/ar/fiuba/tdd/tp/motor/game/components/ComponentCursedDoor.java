package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentCursedDoor extends ComponentDoor {

    public ComponentCursedDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo, ComponentKey keyNotRequired) {
        super(game,roomItLeadsTo,keyNotRequired);
    }

    public ComponentCursedDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo) {
        super(game, roomItLeadsTo, null);
    }

    @Override
    protected Boolean isThisDoorUnlocked() {
        return !(this.game.hasPlayerComponent(this.keyAssociated.getDescription()));
    }

    @Override
    public String getBasicName() {
        return "cursed door";
    }
}
