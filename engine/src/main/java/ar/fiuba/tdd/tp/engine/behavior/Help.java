package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.Game;

public class Help implements Behavior {
    Game game;

    public Help(Game game) {
        this.game = game;
    }

    @Override
    public String execute(String modifier) {
        return game.help();
    }
}
