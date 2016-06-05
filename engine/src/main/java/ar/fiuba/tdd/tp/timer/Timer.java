package ar.fiuba.tdd.tp.timer;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Sender;

/**
 * Created by kevin on 29/05/16.
 */
public interface Timer {

    void update(Context context, Sender sender);

    boolean isFinished();
}
