import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RiddleBuilder implements GameBuilder {

    private static ComponentContainer boat;

    //Names of items, commands and responses
    private static final String GAME_NAME = "Riddle";
    private static final String WOLF_NAME = "wolf";
    private static final String SHEEP_NAME = "sheep";
    private static final String CABBAGE_NAME = "cabbage";
    private static final String BOAT_NAME = "boat";
    private static final String SOUTH_RIVER_NAME = "south";
    private static final String NORTH_RIVER_NAME = "north";

    private static final String CROSS = "cross";
    private static final String LEAVE = "leave";
    private static final String TAKE = "take";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO_WITH = "what can i do with";

    private static final String CANT_TAKE = "You can't see what you are trying to take.";
    private static final String TAKE_SUCCESS = "You have taken ";
    private static final String BOAT_FULL = "Can't take it, the boat is full.";
    private static final String LEAVE_SUCCESS = "You leave ";
    private static final String LEAVE_FAIL_BEGINNING = "There is no ";
    private static final String LEAVE_FAIL_END = " in the boat.";
    private static final String NO_ITEM_ROOM = "There is no such item in this room.";
    private static final String NO_ITEM_BOAT = "There is no such item in this boat.";
    private static final String WON_GAME = "You picked the stick and won the game!";
    private static final String HELP_MESSAGE = "help!!!";

    private static final int BOAT_CARRY_LIMIT = 1;

    public Game build() {
        Game riddle = new Game() {
            public Player player = new Player();

            @Override
            public boolean winCondition() {
                //TODO ver como comprobar
                return false;
            }

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

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

        riddle.getPlayer().addBehavior(CROSS, new Cross(riddle));
        riddle.getPlayer().addBehavior(LOOK_AROUND, new LookAround(riddle));
        riddle.getPlayer().addBehavior(WHAT_CAN_I_DO_WITH, new DirectAction(riddle));
        riddle.getPlayer().addBehavior(TAKE, new DirectAction(riddle));
        riddle.getPlayer().addBehavior(LEAVE, new BoatAction(riddle));

        ComponentInterface wolf = new ComponentSimple(WOLF_NAME);
        wolf.addBehavior(WHAT_CAN_I_DO_WITH, new WhatCanIDo(riddle, wolf));
        wolf.addBehavior(TAKE, new Take(riddle, wolf));
        wolf.addBehavior(LEAVE, new Leave(riddle, wolf));

        ComponentInterface sheep = new ComponentSimple(WOLF_NAME);
        sheep.addBehavior(WHAT_CAN_I_DO_WITH, new WhatCanIDo(riddle, sheep));
        sheep.addBehavior(TAKE, new Take(riddle, sheep));
        sheep.addBehavior(LEAVE, new Leave(riddle, sheep));

        ComponentInterface cabbage = new ComponentSimple(WOLF_NAME);
        cabbage.addBehavior(WHAT_CAN_I_DO_WITH, new WhatCanIDo(riddle, cabbage));
        cabbage.addBehavior(TAKE, new Take(riddle, wolf));
        cabbage.addBehavior(LEAVE, new Leave(riddle, wolf));

        boat = new ComponentContainer(BOAT_NAME);

        ComponentContainer southRiver = new ComponentContainer(SOUTH_RIVER_NAME);
        southRiver.addItem(wolf);
        southRiver.addItem(sheep);
        southRiver.addItem(cabbage);
        riddle.getPlayer().setRoom(southRiver);

        ComponentContainer northRiver = new ComponentContainer(NORTH_RIVER_NAME);

        return riddle;
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

    private static class Cross implements Behavior {
        Game game;

        Cross(Game game) {
            this.game = game;
        }

        public String execute(String modifier) {
            return "Terminar esto que es la logica del juego casi";
        }
    }


    private static class Leave implements Behavior {
        Game game;
        ComponentInterface item;

        Leave(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            if (boat.hasItem(item)) {
                game.getPlayer().putItemInRoom(item);
                return LEAVE_SUCCESS + item.getName() + ".";
            }
            return LEAVE_FAIL_BEGINNING + item.getName() + LEAVE_FAIL_END;
        }
    }

    private static class Take implements Behavior {
        Game game;
        ComponentInterface item;

        Take(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            if (game.getPlayer().seesItemInRoom(item)) {
                if (boat.itemCount() < BOAT_CARRY_LIMIT ) {
                    game.getPlayer().removeItemFromRoom(item);
                    boat.addItem(item);
                    return TAKE_SUCCESS + item.getName() + " to the boat.";
                }
                return BOAT_FULL;
            }
            return CANT_TAKE;
        }
    }

    private static class BoatAction implements Behavior {
        private static final String DIRECT_ACTION_REGEX = "(^.*) (.*)";
        Game game;

        public BoatAction(Game game) {
            this.game = game;
        }


        public String execute(String completeMessage) {
            Pattern commandPattern = Pattern.compile(DIRECT_ACTION_REGEX);
            Matcher commandMatcher = commandPattern.matcher(completeMessage);
            ComponentInterface component = null;
            if (commandMatcher.find()) {
                component = boat.getItem(commandMatcher.group(2));
            }
            if (component != null) {
                return component.doAction(commandMatcher.group(1), completeMessage);
            }

            return NO_ITEM_BOAT;
        }
    }
}