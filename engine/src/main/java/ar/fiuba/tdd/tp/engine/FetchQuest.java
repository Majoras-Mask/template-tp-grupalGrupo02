package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.ArrayList;
import java.util.Arrays;

public class FetchQuest extends Game {

    private ComponentInterface winningCondition;
    private static final String NO_ITEM_IN_ROOM = "There is no such thing in this room.";
    private static String pickCommand = "pick";


    public FetchQuest() {
        super();
        player.addBehavior("look around", new LookAround(this));
        player.addBehavior(pickCommand, new Pickable(this));

        ComponentInterface stick = new ComponentSimple("stick");
        stick.addBehavior(pickCommand, new NormalPick(this, stick));
        winningCondition = stick;
        ComponentContainer room = new ComponentContainer("primerCuarto");
        room.addItem(stick);
        player.setRoom(room);
    }


    private static class Pickable implements Behavior {
        Game game;

        Pickable(Game game) {
            this.game = game;
        }

        @Override
        public String execute(String modifier) {
            //TODO aca deberia tener en el string el mensaje completo, busco el item con alguna regex particular
            //a todos los picks, agarro el item y le digo execute y fin
            String regexParaEsto = "stick";
            ComponentInterface component = game.getPlayer().obtainItemRoom(regexParaEsto);
            if (component != null) {
                return component.doAction(pickCommand, modifier);
            }
            return NO_ITEM_IN_ROOM;
        }
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
