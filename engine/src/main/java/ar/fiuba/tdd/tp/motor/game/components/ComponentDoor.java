package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ComponentDoor extends GameComponent {

    ComponentKey keyAssociated = null;
    ComponentRoom fromRoom;
    ComponentRoom roomItLeadsTo;

    public ComponentDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo, ComponentKey keyAssociated) {
        this.fromRoom = fromRoom;
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyAssociated = keyAssociated;
    }

    public ComponentDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo) {
        this(fromRoom, roomItLeadsTo, null);
    }

    public Boolean matchingKey(GameComponent component) {
        return component == this.keyAssociated;
    }

    public ComponentRoom getWhereItLeadsTo(ZorkTypeGame game) {
        return ( game.getCurrentRoom() == fromRoom ? roomItLeadsTo : fromRoom);
    }

    protected void goToRoom(ZorkTypeGame game) {
        game.setCurrentRoom(getWhereItLeadsTo(game));
    }

    protected void unlockDoor() {
        this.keyAssociated = null;
    }

    @Override
    public String whatCanIDo() {
        return "You can open/close this.";
    }
}
