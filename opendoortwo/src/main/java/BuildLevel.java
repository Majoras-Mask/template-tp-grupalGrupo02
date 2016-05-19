import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;
import ar.fiuba.tdd.tp.engine.rules.PlayerHasRule;

public class BuildLevel {

    private static final String DOOR_NAME = "door";
    private static final String KEY_NAME = "key";
    private static final String ROOM_NAME = "room";
    private static final String BOX_NAME = "box";
    private static final String WINNING_ROOM_NAME = "winning room";

    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";
    private static final String HELP = "help";

    public static void build(Game openDoorTwo) {
        playerBehavior(openDoorTwo);

        ComponentInterface key = new ComponentSimple(KEY_NAME);
        key.addBehavior(PICK, new Pick(openDoorTwo, key));
        key.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(key));

        ComponentContainer box = new ComponentContainer(BOX_NAME);
        box.addItem(key);
        box.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(box));
        box.addBehavior(OPEN, new ContainerDrop(openDoorTwo, box));

        ComponentInterface door = new ComponentSimple(DOOR_NAME);
        ComponentContainer winningRoom = new ComponentContainer(WINNING_ROOM_NAME);
        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        door.addBehavior(OPEN, new Cross(openDoorTwo, room, winningRoom, new PlayerHasRule(openDoorTwo, key)));

        room.addItem(door);
        room.addItem(box);
        openDoorTwo.getPlayer().setRoom(room);

        door.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(door));

        WonGameRule.setWinningRoomName(WINNING_ROOM_NAME);
        WonGameRule.setGame(openDoorTwo);
    }

    private static void playerBehavior(Game openDoorTwo) {
        Player player = openDoorTwo.getPlayer();
        player.addBehavior(LOOK_AROUND, new LookAround(openDoorTwo));
        player.addBehavior(PICK, new DirectActionRoom(openDoorTwo));
        player.addBehavior(OPEN, new DirectActionRoom(openDoorTwo));
        player.addBehavior(WHAT_CAN_I_DO, new DirectActionRoom(openDoorTwo));
        player.addBehavior(HELP, new Help(openDoorTwo));
    }
}
