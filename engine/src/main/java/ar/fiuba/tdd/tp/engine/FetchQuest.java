package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

public class FetchQuest extends Game {

    public FetchQuest() {
        super();
        ComponentInterface stick = new ComponentSimple("stick");
        stick.addBehavior("pick", new NormalPick(this, stick));
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
        return false;
    }
}
