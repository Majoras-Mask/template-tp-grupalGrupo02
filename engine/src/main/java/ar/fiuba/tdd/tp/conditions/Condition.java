package ar.fiuba.tdd.tp.conditions;

import ar.fiuba.tdd.tp.Context;

/**
 * Created by kevin on 28/05/16.
 */
public interface Condition {
    boolean check(Context context);

    Condition and(Condition other);

    Condition or(Condition other);

    Condition not();
}
