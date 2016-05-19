package ar.fiuba.tdd.tp.engine.rules;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class PlayerHasRule implements Rule {

    private static final String PLAYER_NEEDS_ITEM = "You don't have the required item to perform this action.";

    Game game;
    ComponentInterface itemRequired;

    public PlayerHasRule(Game game, ComponentInterface item) {
        this.game = game;
        this.itemRequired = item;
    }

    @Override
    public boolean satisfiesRule() {
        return game.getPlayer().playerHasItem(itemRequired);
    }

    @Override
    public String reasonsOfRuleFail() {
        return PLAYER_NEEDS_ITEM;
    }
}
