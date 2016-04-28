package ar.fiuba.tdd.tp.motor.game.components;

public class BuilderZork {

    private int boxCount = 0;
    private int closetCount = 0;
    private int doorCount = 0;
    private int keyCount = 0;
    private int roomCout = 0;
    private int poissonCount = 0;
    private int thiefCount = 0;

    public BuilderZork() {

    }

    ComponentBox createBox() {
        ComponentBox box = new ComponentBox();
        box.setId(boxCount++);
        return box;
    }

    ComponentCloset createCloset() {
        ComponentCloset closet = new ComponentCloset();
        closet.setId(closetCount++);
        return closet;
    }

    ComponentDoor createDoorWithOpenCondition(ComponentRoom roomFrom, ComponentRoom toRoom,
                                              OpenCondition openCondition) {
        ComponentDoor door = new ComponentDoor(roomFrom, toRoom, openCondition);
        door.setId(doorCount++);
        return door;
    }

    ComponentDoor createCursedDoor(ComponentRoom roomFrom, ComponentRoom toRoom, ComponentKey key) {
        return createDoorWithOpenCondition(roomFrom, toRoom, new OpenConditionObject(key, true));
    }

    ComponentDoor createNormalDoor(ComponentRoom roomFrom, ComponentRoom toRoom, ComponentKey key) {
        return createDoorWithOpenCondition(roomFrom, toRoom, new OpenConditionObject(key, true));
    }

    ComponentKey createKey() {
        ComponentKey key = new ComponentKey();
        key.setId(keyCount++);
        return key;
    }

    ComponentRoom createRoom() {
        ComponentRoom room = new ComponentRoom();
        room.setId(roomCout++);
        return room;
    }


    ComponentPoisson createPoisson() {
        ComponentPoisson poisson = new ComponentPoisson();
        poisson.setId(poissonCount++);
        return poisson;
    }

    ComponentThief createThief() {
        ComponentThief thief = new ComponentThief();
        thief.setId(thiefCount++);
        return thief;
    }


}
