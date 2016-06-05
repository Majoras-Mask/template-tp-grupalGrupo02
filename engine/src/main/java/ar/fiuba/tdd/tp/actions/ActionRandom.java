package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;

import java.util.Random;

public class ActionRandom extends ActionContainer {

    private static Random random = new Random();

    @Override
    public void execute(Context context) {
        int randomIndex = ActionRandom.random.nextInt(actions.size());
        actions.get(randomIndex).execute(context);
    }
}
