package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentDoor extends GameComponent {

    ComponentKey keyRequired = null;
    ComponentRoom roomItLeadsTo;

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo, ComponentKey keyRequired) {
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyRequired = keyRequired;
        this.game = game;
    }

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo) {
        this(game, roomItLeadsTo, null);
    }

    public Boolean matchingKey(GameComponent component) {
        return component.getDescription().equals(this.keyRequired.getDescription());
    }

    public void unlockDoor() {
        this.keyRequired = null;
    }

    public ComponentRoom getWhereItLeadsTo() {
        return this.roomItLeadsTo;
    }

    public Boolean isThisDoorUnlocked() {
        return this.keyRequired == null;
    }

    private void goToRoom(ZorkTypeGame game) {
        game.setCurrentRoom(getWhereItLeadsTo());
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
