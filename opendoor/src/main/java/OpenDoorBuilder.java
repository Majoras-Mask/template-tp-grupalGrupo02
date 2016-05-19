import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.behavior.DirectAction;
import ar.fiuba.tdd.tp.engine.behavior.WhatCanIDo;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class OpenDoorBuilder implements GameBuilder {

    private static final String GAME_NAME = "Open Door";
    private static final String DOOR_NAME = "door";
    private static final String KEY_NAME = "key";
    private static final String ROOM_NAME = "room";
    private static final String WINNING_ROOM_NAME = "winning room";

    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";

    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";
    private static final String OPEN_DOOR_SUCCESS = "You open the door and go to the other side.";
    private static final String OPEN_DOOR_FAIL = "The door is locked, it requires a key.";

    private static final String NO_ITEM_ROOM = "There is no such item in this room.";
    private static final String WON_GAME = "You won the game by opening the door!";
    private static final String HELP_MESSAGE = "help!!!";


    public Game build() {
        Game openDoor = new Game() {
            public Player player = new Player();

            @Override
            public boolean winCondition() {
                return this.getPlayer().currentRoomName().equals(WINNING_ROOM_NAME);
            }

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public String help() {
                return HELP_MESSAGE;
            }

            @Override
            public String command(String clientMessage) {
                String response = player.doCommand(clientMessage);
                if (winCondition()) {
                    response = WON_GAME;
                }
                return response;
            }
        };

        openDoor.getPlayer().addBehavior(LOOK_AROUND, new LookAround(openDoor));
        openDoor.getPlayer().addBehavior(PICK, new DirectAction(openDoor));
        openDoor.getPlayer().addBehavior(OPEN, new DirectAction(openDoor));
        openDoor.getPlayer().addBehavior(WHAT_CAN_I_DO, new DirectAction(openDoor));

        ComponentInterface key = new ComponentSimple(KEY_NAME);
        key.addBehavior(PICK, new NormalPick(openDoor, key));
        key.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(key));

        ComponentInterface door = new ComponentSimple(DOOR_NAME);
        ComponentContainer winningRoom = new ComponentContainer(WINNING_ROOM_NAME);
        door.addBehavior(OPEN, new LockedDoorOpen(openDoor, key, winningRoom));
        door.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(door));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(key);
        room.addItem(door);
        openDoor.getPlayer().setRoom(room);

        return openDoor;
    }

    //Behaviors

    private static class LockedDoorOpen implements Behavior {
        Game game;
        ComponentInterface keyRequired;
        ComponentContainer roomItLeadsTo;

        LockedDoorOpen(Game game, ComponentInterface key, ComponentContainer roomItLeadsTo) {
            this.game = game;
            this.keyRequired = key;
            this.roomItLeadsTo = roomItLeadsTo;
        }

        public String execute(String modifier) {
            if (game.getPlayer().playerHasItem(keyRequired)) {
                game.getPlayer().setRoom(roomItLeadsTo);
                return OPEN_DOOR_SUCCESS;
            }
            return OPEN_DOOR_FAIL;
        }
    }

    private static class LookAround implements Behavior {
        Game game;

        LookAround(Game game) {
            this.game = game;
        }

        public String execute(String modifier) {
            StringBuffer message = new StringBuffer();
            message.append(game.getPlayer().currentRoomName() + " has:");
            for (String component : game.getPlayer().listOfWhatISee()) {
                message.append(" A " + component + ".");
            }

            return message.toString();
        }
    }

    private static class NormalPick implements Behavior {
        Game game;
        ComponentInterface item;

        NormalPick(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            if (this.game.getPlayer().removeItemFromRoom(item)) {
                this.game.getPlayer().addItemToInventory(item);
                return PICK_SUCCESS + item.getName();
            }
            return CANT_PICK;
        }
    }
}