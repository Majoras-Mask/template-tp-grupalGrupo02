package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class Cross implements Behavior {

    Game game;
    ComponentContainer to;

    public Cross(Game game, ComponentContainer to) {
        this.game = game;
        this.to = to;
    }

    public String execute(String modifier) {
        game.getPlayer().setRoom(to);
        return "You go to " + to.getName();
    }

}