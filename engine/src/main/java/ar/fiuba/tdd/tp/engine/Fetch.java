package ar.fiuba.tdd.tp.engine;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Fetch extends Game {
    private final Room room;
    private final Item stick;

    public Fetch() {
        super();
        room = new Room("room");
        stick = new Item("stick");
        room.putItem(stick);
        player.setRoom(room);
    }

    boolean winCondition() {
        return player.hasItem(stick);
    }
}
