package ar.fiuba.tdd.tp.motor.game.components;

public class BuilderZork {

    private int boxCount = 0;
    private int closetCount = 0;
    private int doorCount = 0;
    private int keyCount = 0;
    private int roomCout = 0;
    private int poissonCount = 0;
    private int thiefCount = 0;
    private int antidoteCount;

    public BuilderZork() {

    }

    private void setID(GameComponent component, int id) {
        component.setId(id);
    }

    public ComponentBox createBox() {
        ComponentBox box = new ComponentBox();
        setID(box,boxCount++);
        return box;
    }

    public ComponentCloset createCloset() {
        ComponentCloset closet = new ComponentCloset();
        setID(closet, closetCount++);
        return closet;
    }

    public ComponentDoor createDoorWithOpenCondition(ComponentRoom roomFrom, ComponentRoom toRoom,
                                              OpenCondition openCondition) {
        ComponentDoor door = new ComponentDoor(roomFrom, toRoom, openCondition);
        setID(door, doorCount++);
        roomFrom.addComponent(door);
        toRoom.addComponent(door);
        return door;
    }

    public ComponentDoor createCursedDoor(ComponentRoom roomFrom, ComponentRoom toRoom, ComponentKey key) {
        if (key == null) {
            return createDoorWithOpenCondition(roomFrom, toRoom, null);
        }
        return createDoorWithOpenCondition(roomFrom, toRoom, new OpenConditionObject(key, false));
    }

    public ComponentDoor createNormalDoor(ComponentRoom roomFrom, ComponentRoom toRoom, ComponentKey key) {
        if (key == null) {
            return createDoorWithOpenCondition(roomFrom, toRoom, null);
        }
        return createDoorWithOpenCondition(roomFrom, toRoom, new OpenConditionObject(key, true));
    }

    public ComponentKey createKey() {
        ComponentKey key = new ComponentKey();
        setID(key,keyCount++);
        return key;
    }

    public ComponentRoom createRoom() {
        ComponentRoom room = new ComponentRoom();
        room.setId(roomCout++);
        return room;
    }


    public ComponentPoison createPoisson() {
        ComponentPoison poisson = new ComponentPoison();
        poisson.setId(poissonCount++);
        return poisson;
    }

    public ComponentThief createThief() {
        ComponentThief thief = new ComponentThief();
        thief.setId(thiefCount++);
        return thief;
    }

    public ComponentAntidote createAntidote() {
        ComponentAntidote antidote = new ComponentAntidote();
        antidote.setId(antidoteCount++);
        return antidote;
    }


}
