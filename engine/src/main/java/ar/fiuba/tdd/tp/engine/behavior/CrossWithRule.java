package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class CrossWithRule implements Behavior {

    private static final String CROSS_SUCCESS = "Crossed to the other side.";

    Game game;
    Rule rule;
    ComponentContainer to;

    public CrossWithRule(Game game, ComponentContainer to, Rule rule) {
        this.game = game;
        this.to = to;
        this.rule = rule;
    }

    public String execute(String modifier) {
        if (rule.satisfiesRule()) {
            game.getPlayer().setRoom(to);
            return CROSS_SUCCESS;
        }
        return rule.reasonsOfRuleFail();
    }

}
