package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.Sender;
import ar.fiuba.tdd.tp.conditions.Condition;

import java.util.HashMap;

/**
 * Created by kevin on 05/06/16.
 */
public class TimerConcrete implements Timer {
    private static final long SECOND_TO_MILISECOND_CONVERTER = 1000;

    protected long miliSecondsRequired;
    protected long currentMiliSeconds;
    protected HashMap<Condition, String> responses = new HashMap<>();
    protected HashMap<Condition,Element> actions = new HashMap<>();


    public TimerConcrete(long seconds) {
        long auxiliaryVariable = System.currentTimeMillis();
        this.miliSecondsRequired = auxiliaryVariable + secondToMiliSecond(seconds);
        this.currentMiliSeconds = auxiliaryVariable;
    }

    private long secondToMiliSecond(long second) {
        return second * SECOND_TO_MILISECOND_CONVERTER;
    }

    @Override
    public void setCondition(Condition condition, Element toExecute, String response) {
        this.actions.put(condition, toExecute);
        this.responses.put(condition, response);
    }

    protected void timeExpiredAction(Context context, Sender sender) {
        for (Condition condition : actions.keySet()) {
            if (condition.check(context)) {
                actions.get(condition).execute(context);
                String message = responses.get(condition);
                sender.sendAll(message);
                return;
            }
        }
    }

    protected void timerExpiredHook(Context context, Sender sender) {
        return;
    }

    @Override
    public void update(Context context, Sender sender) {
        update(context, sender, 0);
    }

    @Override
    public void update(Context context, Sender sender, long milisecondsForward) {
        if (isFinished()) {
            return;
        }

        this.currentMiliSeconds = System.currentTimeMillis() + milisecondsForward;
        if (this.currentMiliSeconds >= miliSecondsRequired) {
            // Expiro el timer
            timeExpiredAction(context, sender);
            timerExpiredHook(context, sender);
        }
    }

    @Override
    public boolean isFinished() {
        return (this.currentMiliSeconds >= miliSecondsRequired);
    }
}
