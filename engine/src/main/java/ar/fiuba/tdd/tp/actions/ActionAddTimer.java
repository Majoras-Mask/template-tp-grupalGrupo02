package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.timer.Timer;

/**
 * Created by kevin on 07/06/16.
 */
public class ActionAddTimer implements Action{

    public Timer timer;
    public Game game;

    public ActionAddTimer(Timer timer, Game game) {
        this.timer = timer;
        this.game = game;
    }

    @Override
    public void execute(Context context) {
        this.game.addTimer(timer);
    }
}
