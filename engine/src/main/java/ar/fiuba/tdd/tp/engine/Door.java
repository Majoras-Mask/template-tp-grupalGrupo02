package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public class Door {
    private final String name;
    private final Room room1;
    private final Room room2;

    public Door(String name, Room room1, Room room2) {
        this.name = name;
        this.room1 = room1;
        this.room2 = room2;
    }

    public Room getOtherRoom(Room room) {
        if (room1.equals(room)) {
            return room2;
        } else {
            return room1;
        }
    }
}
