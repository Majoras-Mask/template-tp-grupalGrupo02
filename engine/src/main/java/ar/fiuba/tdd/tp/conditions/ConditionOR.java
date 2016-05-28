package ar.fiuba.tdd.tp.conditions;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionOR implements Condition {
    private Condition condition1;
    private Condition condition2;

    public ConditionOR(Condition condition1, Condition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;

    }

    @Override
    public boolean check() {
        return condition1.check() || condition2.check();
    }
}
