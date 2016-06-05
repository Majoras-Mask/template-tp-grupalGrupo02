package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.conditions.Condition;

import java.util.HashMap;

/**
 * Created by kevin on 04/06/16.
 */
public class PeriodicTimer extends TimerConcrete {


    public PeriodicTimer(int ticks) {
        super(ticks);
    }

    @Override
    protected void timerExpiredHook(Context context, Sender sender) {
        super.timerExpiredHook(context, sender);
        this.currentTicks = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
