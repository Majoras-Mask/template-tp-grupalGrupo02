package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public class OpenDoor1 extends Game{
    private final Room room1;
    private final Room room2;
    private final Item key;
    private final Door door;

    public OpenDoor1() {
        super();
        room1 = new Room("room1");
        room2 = new Room("room2");
        key = new Item("key");
        door = new Door(room1, room2);
        room1.putItem(key);
        room1.setDoor(door);
        room2.setDoor(door);
        player.setRoom(room1);
    }

    boolean winCondition() {
        return player.isInRoom(room2);
    }
}
