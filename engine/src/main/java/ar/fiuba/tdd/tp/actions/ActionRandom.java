package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.RandomGenerator;

import java.util.Random;

public class ActionRandom extends ActionContainer {

    private Game game;

    public ActionRandom(Game game) {
        this.game = game;
    }

    @Override
    public void execute(Context context) {
        int randomIndex = game.getRandomGenerator().getRandom(actions.size());
        actions.get(randomIndex).execute(context);
    }

}
