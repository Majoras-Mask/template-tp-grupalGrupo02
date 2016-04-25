package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentNormalDoor extends ComponentDoor {

    public ComponentNormalDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo, ComponentKey keyRequired) {
        super(game,roomItLeadsTo,keyRequired);
    }

    public ComponentNormalDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo) {
        super(game, roomItLeadsTo, null);
    }

    @Override
    public Boolean isThisDoorUnlocked() {
        return this.keyAssociated == null;
    }

    @Override
    public String getBasicName() {
        return "door";
    }

    @Override
    public Boolean close() {
        return true;
    }

    @Override
    public Boolean open() {
        if (isThisDoorUnlocked()) {
            goToRoom(this.game);
            return true;
        }
        for (GameComponent component: this.game.getPlayerItems()) {
            if (matchingKey(component)) {
                unlockDoor();
                goToRoom(this.game);
                return true;
            }
        }
        return false;
    }
}
