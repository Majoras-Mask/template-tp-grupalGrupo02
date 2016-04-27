package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentNormalDoor extends ComponentDoor {

    public ComponentNormalDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo, ComponentKey keyRequired) {
        super(fromRoom, roomItLeadsTo,keyRequired);
    }

    public ComponentNormalDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo) {
        super(fromRoom, roomItLeadsTo, null);
    }

    public Boolean isThisDoorUnlocked() {
        return this.keyAssociated == null;
    }

    @Override
    public String getBasicName() {
        return "door";
    }

    @Override
    public Boolean close(ZorkTypeGame game) {
        return true;
    }

    @Override
    public Boolean open(ZorkTypeGame game) {
        if (isThisDoorUnlocked()) {
            goToRoom(game);
            return true;
        }
        for (GameComponent component: game.getPlayerItems()) {
            if (matchingKey(component)) {
                unlockDoor();
                goToRoom(game);
                return true;
            }
        }
        return false;
    }
}
