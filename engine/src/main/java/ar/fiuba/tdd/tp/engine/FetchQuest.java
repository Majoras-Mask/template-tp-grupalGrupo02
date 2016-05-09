package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.ArrayList;
import java.util.Arrays;

public class FetchQuest extends Game {

    private ComponentInterface winningCondition;
    private ArrayList<String> possibleCommands = new ArrayList<String>(
            Arrays.asList("pick"));

    public FetchQuest() {
        super();
        ComponentInterface stick = new ComponentSimple("stick");
        stick.addBehavior("pick", new NormalPick(this, stick));
        winningCondition = stick;
        player.setListOfPossibleCommands(possibleCommands);
        ComponentContainer room = new ComponentContainer("primerCuarto");
        //Room room = new Room("primerCuarto");
        room.addItem(stick);
        player.setRoom(room);
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
            if (this.game.getPlayer().removeItem(item)) {
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
}
