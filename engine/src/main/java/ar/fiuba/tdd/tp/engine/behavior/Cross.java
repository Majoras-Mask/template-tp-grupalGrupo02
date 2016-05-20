package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class Cross implements Behavior {

    private static final String CROSS_SUCCESS = "Crossed to the other side.";

    Game game;
    Rule rule;
    ComponentContainer from;
    ComponentContainer to;

    public Cross(Game game, ComponentContainer from, ComponentContainer to, Rule rule) {
        this.game = game;
        this.from = from;
        this.to = to;
        this.rule = rule;
    }

    public String execute(String modifier) {
        if (rule.satisfiesRule()) {
            game.getPlayer().setRoom(to);
//            swapFromTo();
            return CROSS_SUCCESS;
        }
        return rule.reasonsOfRuleFail();
    }

    private void swapFromTo() {
        ComponentContainer temp = this.from;
        this.from = this.to;
        this.to = temp;
    }
}
