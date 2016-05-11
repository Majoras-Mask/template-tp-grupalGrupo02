package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.behavior.DirectAction;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchQuest extends Game {

    private ComponentInterface winningCondition;
    private static final String STICK_NAME = "stick";
    private static final String ROOM_NAME = "room";
    private static final String PICK = "pick";

    private static final String LOOK_AROUND = "look around";


    public FetchQuest() {
        super();
        player.addBehavior(LOOK_AROUND, new LookAround(this));
        player.addBehavior(PICK, new DirectAction(this));

        ComponentInterface stick = new ComponentSimple(STICK_NAME);
        stick.addBehavior(PICK, new NormalPick(this, stick));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(stick);
        player.setRoom(room);

        winningCondition = stick;
    }

    private static class LookAround implements Behavior {
        Game game;

        LookAround(Game game) {
            this.game = game;
        }

        @Override
        public String execute(String modifier) {
            game.getPlayer().listOfWhatISee();
            //TODO hacer un foreach para que tire lo que ve
            return "tiraste look around";
        }
    }

    private static class NormalPick implements Behavior {
        Game game;
        ComponentInterface item;

        NormalPick(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        @Override
        public String execute(String modifier) {
            if (this.game.getPlayer().removeItemFromRoom(item)) {
                this.game.getPlayer().addItemToInventory(item);
                return "Picked the item";
            }
            return "Can't pick.";
        }
    }

    @Override
    boolean winCondition() {
        return this.getPlayer().playerHasItem(winningCondition);
    }

    @Override
    public String noItemInRoom() {
        return "There is no such item in this room.";
    }
}
