import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

public class BuildLevel {
    private static final String CROSS = "cross";
    private static final String LEAVE = "leave";
    private static final String TAKE = "take";
    private static final String HELP = "help";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO_WITH = "what can i do with";

    private static final String WOLF_NAME = "wolf";
    private static final String SHEEP_NAME = "sheep";
    private static final String CABBAGE_NAME = "cabbage";
    private static final String BOAT_NAME = "boat";
    private static final String SOUTH_RIVER_NAME = "south";
    private static final String NORTH_RIVER_NAME = "north";


    private static final String CROSS_SUCCESS = "Crossed to the other side.";

    public static void build(Game riddle) {
        buildPlayerActions(riddle);

        buildItemsAndItsActions(riddle);
    }

    private static void buildItemsAndItsActions(Game riddle) {
        ComponentInterface wolf = buildRiddleComponent(WOLF_NAME, riddle);
        ComponentInterface sheep = buildRiddleComponent(SHEEP_NAME, riddle);
        ComponentInterface cabbage = buildRiddleComponent(CABBAGE_NAME, riddle);

        ComponentContainer southRiver = new ComponentContainer(SOUTH_RIVER_NAME);
        southRiver.addItem(wolf);
        southRiver.addItem(sheep);
        southRiver.addItem(cabbage);
        riddle.getPlayer().setRoom(southRiver);

        ComponentContainer northRiver = new ComponentContainer(NORTH_RIVER_NAME);

        ComponentInterface boat = buildBoat(riddle, southRiver, northRiver);
        southRiver.addItem(boat);

        GameRules.setGame(riddle);
        GameRules.setCabbage(cabbage);
        GameRules.setSheep(sheep);
        GameRules.setWolf(wolf);
        GameRules.setNorthRiver(northRiver);
    }

    private static ComponentInterface buildBoat(Game riddle, ComponentContainer from, ComponentContainer to) {
        ComponentInterface component = new ComponentSimple(BOAT_NAME);
        component.addBehavior(CROSS, new Crossing(riddle, from, to));
        return component;
    }

    private static ComponentInterface buildRiddleComponent(String name, Game riddle) {
        ComponentInterface component = new ComponentSimple(name);
        component.addBehavior(WHAT_CAN_I_DO_WITH, new WhatCanIDo(component));
        component.addBehavior(TAKE, new Pick(riddle, component));
        component.addBehavior(LEAVE, new Drop(riddle, component));
        return component;
    }

    private static void buildPlayerActions(Game riddle) {
        Player player = riddle.getPlayer();
        player.setInventoryLimit(1);
        player.addBehavior(CROSS, new DirectActionRoom(riddle));
        player.addBehavior(LOOK_AROUND, new LookAround(riddle));
        player.addBehavior(WHAT_CAN_I_DO_WITH, new DirectActionRoom(riddle));
        player.addBehavior(TAKE, new DirectActionRoom(riddle));
        player.addBehavior(LEAVE, new DirectActionPlayer(riddle));
        player.addBehavior(HELP, new Help(riddle));
    }

    private static class Crossing implements Behavior {
        Game game;
        ComponentContainer from;
        ComponentContainer to;

        Crossing(Game game, ComponentContainer from, ComponentContainer to) {
            this.game = game;
            this.from = from;
            this.to = to;
        }

        public String execute(String modifier) {
            if (GameRules.boatSafeToGo()) {
                game.getPlayer().setRoom(to);
                swapFromTo();
                return CROSS_SUCCESS;
            }
            return GameRules.boatWhyNotSafeToGo();
        }

        private void swapFromTo() {
            ComponentContainer temp = this.from;
            this.from = this.to;
            this.to = temp;
        }
    }

}
