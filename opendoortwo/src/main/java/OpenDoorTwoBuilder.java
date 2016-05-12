import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenDoorTwoBuilder implements GameBuilder {

    private static final String GAME_NAME = "Open Door Two";
    private static final String DOOR_NAME = "door";
    private static final String KEY_NAME = "key";
    private static final String ROOM_NAME = "room";
    private static final String BOX_NAME = "box";
    private static final String WINNING_ROOM_NAME = "winning room";

    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";

    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";
    private static final String OPEN_DOOR_SUCCESS = "You open the door and go to the other side.";
    private static final String OPEN_DOOR_FAIL = "The door is locked, it requires a key.";
    private static final String OPEN_BOX = "You open the box.";

    private static final String NO_ITEM_ROOM = "There is no such item in this room.";
    private static final String WON_GAME = "You won the game by opening the door!";
    private static final String HELP_MESSAGE = "help!!!";


    public Game build() {
        Game openDoorTwo = new Game() {
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

        openDoorTwo.getPlayer().addBehavior(LOOK_AROUND, new LookAround(openDoorTwo));
        openDoorTwo.getPlayer().addBehavior(PICK, new DirectAction(openDoorTwo));
        openDoorTwo.getPlayer().addBehavior(OPEN, new DirectAction(openDoorTwo));
        openDoorTwo.getPlayer().addBehavior(WHAT_CAN_I_DO, new DirectAction(openDoorTwo));

        ComponentInterface key = new ComponentSimple(KEY_NAME);
        key.addBehavior(PICK, new NormalPick(openDoorTwo, key));
        key.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(openDoorTwo, key));

        ComponentContainer box = new ComponentContainer(BOX_NAME);
        box.addItem(key);
        box.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(openDoorTwo, box));
        box.addBehavior(OPEN, new BoxOpen(openDoorTwo, box));

        ComponentInterface door = new ComponentSimple(DOOR_NAME);
        ComponentContainer winningRoom = new ComponentContainer(WINNING_ROOM_NAME);
        door.addBehavior(OPEN, new LockedDoorOpen(openDoorTwo, key, winningRoom));
        door.addBehavior(WHAT_CAN_I_DO, new WhatCanIDo(openDoorTwo, door));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(door);
        room.addItem(box);
        openDoorTwo.getPlayer().setRoom(room);

        return openDoorTwo;
    }

    //Behaviors
    public static class DirectAction implements Behavior {
        private static final String DIRECT_ACTION_REGEX = "(^.*) (.*)";
        Game game;

        public DirectAction(Game game) {
            this.game = game;
        }

        public String execute(String completeMessage) {
            Pattern commandPattern = Pattern.compile(DIRECT_ACTION_REGEX);
            Matcher commandMatcher = commandPattern.matcher(completeMessage);
            ComponentInterface component = null;
            if (commandMatcher.find()) {
                component = game.getPlayer().obtainItemRoom(commandMatcher.group(2));
            }
            if (component != null) {
                return component.doAction(commandMatcher.group(1), completeMessage);
            }

            return NO_ITEM_ROOM;
        }
    }

    private static class WhatCanIDo implements Behavior {
        Game game;
        ComponentInterface item;

        WhatCanIDo(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            StringBuffer message = new StringBuffer();
            message.append("You can: ");
            for (String action : item.getListOfActions()) {
                message.append(action + ". ");
            }
            return message.toString();
        }
    }

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

    private static class BoxOpen implements Behavior {
        Game game;
        ComponentContainer item;

        BoxOpen(Game game, ComponentContainer item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            StringBuffer message = new StringBuffer();
            message.append(OPEN_BOX);
            for(String itemName : item.listOfComponents()) {
                ComponentInterface component = item.removeItem(itemName);
                game.getPlayer().putItemInRoom(component);
                message.append(itemName + " dropped. ");
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