import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

public class BuildLevel {
    //names
    private static final String DOOR_NAME = "door";
    private static final String KEY_NAME = "key";
    private static final String ROOM_NAME = "room";
    private static final String WINNING_ROOM_NAME = "winning room";
    //actions
    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String HELP = "help";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";

    public static void build(Game openDoor) {
        openDoor.getPlayer().addBehavior(LOOK_AROUND, new LookAround(openDoor));
        openDoor.getPlayer().addBehavior(PICK, new DirectActionRoom(openDoor));
        openDoor.getPlayer().addBehavior(OPEN, new DirectActionRoom(openDoor));
        openDoor.getPlayer().addBehavior(WHAT_CAN_I_DO, new DirectActionRoom(openDoor));
        openDoor.getPlayer().addBehavior(HELP, new Help(openDoor));

        ComponentInterface key = new ComponentSimple(KEY_NAME);
        key.addBehavior(PICK, new Pick(openDoor, key));
        key.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(key));

        ComponentInterface door = new ComponentSimple(DOOR_NAME);
        ComponentContainer winningRoom = new ComponentContainer(WINNING_ROOM_NAME);
        door.addBehavior(OPEN, new LockedDoorOpen(openDoor, key, winningRoom));
        door.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(door));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(key);
        room.addItem(door);
        openDoor.getPlayer().setRoom(room);
    }

    public static String getWinningRoomName() {
        return WINNING_ROOM_NAME;
    }
}
