package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ComponentDoor extends GameComponent {

    ComponentKey keyAssociated = null;
    ComponentRoom roomItLeadsTo;

    public ComponentDoor(ComponentRoom roomItLeadsTo, ComponentKey keyAssociated) {
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyAssociated = keyAssociated;
    }

    public ComponentDoor(ComponentRoom roomItLeadsTo) {
        this(roomItLeadsTo, null);
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

    protected void unlockDoor() {
        this.keyAssociated = null;
    }

    @Override
    public String whatCanIDo() {
        return "You can open/close this.";
    }
}
