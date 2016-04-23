package ar.fiuba.tdd.tp.motor.game.components;

public class ComponentDoor extends GameComponents {

    ComponentKey keyRequired = null;
    ComponentRoom roomItLeadsTo;

    public ComponentDoor(ComponentRoom roomItLeadsTo, ComponentKey keyRequired) {
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyRequired = keyRequired;
    }

    public ComponentDoor(ComponentRoom roomItLeadsTo) {
        this(roomItLeadsTo, null);
    }

    public Boolean matchingKey(ComponentKey key) {
        return key.getDescription().equals(this.keyRequired.getDescription());
    }

    public void unlockDoor() {
        this.keyRequired = null;
    }

    public ComponentRoom getWhereItLeadsTo() {
        return this.roomItLeadsTo;
    }

    @Override
    public String getDescription() {
        return "Door" + String.valueOf(this.id);
    }

    @Override
    public Boolean pick() {
        return false;
    }

    @Override
    public Boolean close() {
        return true;
    }

    @Override
    public Boolean open() {
        return true;
    }
}
