package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentCursedDoor extends ComponentDoor {

    public ComponentCursedDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo, ComponentKey keyNotRequired) {
        super(fromRoom, roomItLeadsTo,keyNotRequired);
    }

    public ComponentCursedDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo) {
        super(fromRoom, roomItLeadsTo, null);
    }

    protected Boolean isThisDoorUnlocked(ZorkTypeGame game) {
        return !(game.hasPlayerComponent(this.keyAssociated.getDescription()));
    }

    @Override
    public String getBasicName() {
        return "cursed door";
    }
}
