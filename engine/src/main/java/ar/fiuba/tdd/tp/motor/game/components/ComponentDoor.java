package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ComponentDoor extends GameComponent {

    ComponentKey keyAssociated = null;
    ComponentRoom roomItLeadsTo;

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo, ComponentKey keyAssociated) {
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyAssociated = keyAssociated;
        this.game = game;
    }

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo) {
        this(game, roomItLeadsTo, null);
    }

    public Boolean matchingKey(GameComponent component) {
        return component.getDescription().equals(this.keyAssociated.getDescription());
    }

    public ComponentRoom getWhereItLeadsTo() {
        return this.roomItLeadsTo;
    }

    protected void goToRoom(ZorkTypeGame game) {
        game.setCurrentRoom(getWhereItLeadsTo());
    }

    protected abstract Boolean isThisDoorUnlocked();

    protected void unlockDoor() {
        this.keyAssociated = null;
    }
}
