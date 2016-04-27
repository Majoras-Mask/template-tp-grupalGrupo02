package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentDoor extends GameComponent {

    private ComponentRoom fromRoom;
    private ComponentRoom roomItLeadsTo;
    private boolean locked = false;
    private OpenCondition openCondition;

    public ComponentDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo,
                         OpenCondition openCondition) {
        this.fromRoom = fromRoom;
        this.roomItLeadsTo = roomItLeadsTo;
        this.openCondition = openCondition;
        if (openCondition != null) {
            this.locked = true;
        }
    }

    public ComponentDoor(ComponentRoom fromRoom, ComponentRoom roomItLeadsTo) {
        this(fromRoom, roomItLeadsTo, null);
    }

    public ComponentRoom getWhereItLeadsTo(ZorkTypeGame game) {
        return ( game.getCurrentRoom() == fromRoom ? roomItLeadsTo : fromRoom);
    }

    protected void goToRoom(ZorkTypeGame game) {
        game.setCurrentRoom(getWhereItLeadsTo(game));
    }

    protected void unlockDoor() {
        this.locked = false;
    }

    @Override
    public String whatCanIDo() {
        return "You can open/close this.";
    }

    public Boolean isThisDoorUnlocked() {
        return !this.locked;
    }

    @Override
    public Boolean open(ZorkTypeGame game) {
        if (isThisDoorUnlocked()) {
            goToRoom(game);
            return true;
        }
        if (openCondition.mustOpen(game)) {
            unlockDoor();
            goToRoom(game);
            return true;
        }

        return false;
    }

    @Override
    public String getBasicName() {
        return "door";
    }

    @Override
    public Boolean close(ZorkTypeGame game) {
        return true;
    }
}
