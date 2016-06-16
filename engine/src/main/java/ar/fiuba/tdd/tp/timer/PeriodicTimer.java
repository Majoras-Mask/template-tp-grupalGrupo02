package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Sender;

/**
 * Created by kevin on 04/06/16.
 */
public class PeriodicTimer extends TimerConcrete {


    public PeriodicTimer(long milliseconds) {
        super(milliseconds);
    }

    @Override
    protected void timerExpiredHook(Context context, Sender sender) {
        super.timerExpiredHook(context, sender);
        this.currentMilliSeconds = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
